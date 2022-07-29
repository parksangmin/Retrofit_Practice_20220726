package com.sangmin.retrofit_practice_20220726

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.sangmin.retrofit_practice_20220726.databinding.ActivityDetailTopicBinding
import com.sangmin.retrofit_practice_20220726.databinding.ActivitySignUpBinding
import com.sangmin.retrofit_practice_20220726.datas.BasicResponse
import com.sangmin.retrofit_practice_20220726.datas.TopicData
import com.sangmin.retrofit_practice_20220726.utils.ContextUtil
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTopicActivity : BaseActivity() {

    lateinit var mBinding: ActivityDetailTopicBinding
    lateinit var mTopicData: TopicData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_topic)
        mTopicData = intent.getSerializableExtra("topicData") as TopicData
        Log.d("토픽 데이터", mTopicData.toString())
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        mBinding.vote1Btn.setOnClickListener {
            voteSide(mTopicData.sides[0].id)

        }
        mBinding.vote2Btn.setOnClickListener {
            voteSide(mTopicData.sides[1].id)
        }
        backBtn.setOnClickListener {
            finish()
        }

        userImg.setOnClickListener {
//            프로필 화면으로 이동
            finish()
        }
    }

    override fun setValues() {
//        가져온 데이터 UI 처리 등등
        backBtn.visibility = View.VISIBLE
        userImg.visibility = View.VISIBLE

        getDetailTopicDataFromServer()

        setTopicDataToUi()

    }


    fun getDetailTopicDataFromServer() {
        val token = ContextUtil.getLoginToken(mContext)
        apiList.getRequestDetailTopic(token, mTopicData.id)
            .enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {
                        val br = response.body()!!

                        mTopicData = br.data.topic

                    } else {
                        val errorBodyStr = response.errorBody()!!.string()
                        val jsonObj = JSONObject(errorBodyStr)
                        val message = jsonObj.getString("message")

                        Log.d("상세 토픽 조회 오류", message)


                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }
            })


    }

    //    가져온 토픽 데이터를 UI에 반영하는 코드
    fun setTopicDataToUi() {
        mBinding.titleTxt.text = mTopicData.title
        Glide.with(mContext)
            .load(mTopicData.img_url)
            .into(mBinding.backgroundImg)

        mBinding.side1titleTxt.text = mTopicData.sides[0].title
        mBinding.side2titleTxt.text = mTopicData.sides[1].title

        mBinding.side1VoteCountTxt.text = "${mTopicData.sides[0].vote_count}표"
        mBinding.side2VoteCountTxt.text = "${mTopicData.sides[1].vote_count}표"


    }

    fun voteSide(sideId : Int){
        val token = ContextUtil.getLoginToken(mContext)
        apiList.getRequestTopicVote(token, sideId).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful){
                    val br = response.body()!!

                    Toast.makeText(mContext, br.message, Toast.LENGTH_SHORT).show()

                    mTopicData = br.data.topic

                    setTopicDataToUi()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })

    }


}
