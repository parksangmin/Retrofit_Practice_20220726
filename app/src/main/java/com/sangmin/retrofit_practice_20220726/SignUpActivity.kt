package com.sangmin.retrofit_practice_20220726

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.sangmin.retrofit_practice_20220726.databinding.ActivitySignUpBinding
import com.sangmin.retrofit_practice_20220726.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var mBinding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {
//        이메일 중복 확인버튼 클릭 이벤트 처리
        mBinding.checkEmailBtn.setOnClickListener {
            val inputEmail = mBinding.emailEdt.text.toString()


            apiList.getRequestCheckValue("EMAIL", inputEmail)
                .enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            val br = response.body()!!
                            Log.d("응답", br.toString())

                        }
                        else {

                        }

                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })



        }

    }

    override fun setValues() {

    }

}