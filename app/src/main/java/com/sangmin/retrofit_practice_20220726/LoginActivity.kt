package com.sangmin.retrofit_practice_20220726


import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import org.json.JSONObject
import com.sangmin.retrofit_practice_20220726.api.APIList
import com.sangmin.retrofit_practice_20220726.api.ServerApi
import com.sangmin.retrofit_practice_20220726.databinding.ActivityLoginBinding
import com.sangmin.retrofit_practice_20220726.datas.BaseResponse
import retrofit2.*

class LoginActivity : BaseActivity() {

    lateinit var mBinding : ActivityLoginBinding

//    retrofit 멤버 변수
    lateinit var retrofit: Retrofit
    lateinit var apiList: APIList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)



//        retrofit 관련 클래스 및 인터페이스 초기화
        retrofit = ServerApi.getRetrofit()
        apiList = retrofit.create(APIList::class.java)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {
        mBinding.loginBtn.setOnClickListener {
            val inputEmail = mBinding.emailEdt.text.toString()
            val inputPw = mBinding.passwordEdt.text.toString()

//            로그인 API 연결 로직
            apiList.getRequestLogin(inputEmail, inputPw).enqueue(object : Callback<JSONObject>{
                override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
//           실제로 response가 도달했을 때
                    val responseStr = response.toString()
                    Log.d("로그인",responseStr)
                    if (response.isSuccessful) {
//                        응답이 성공 (로그인 로직 성공 - id / pw 일치)
                        val rb = response.body()
                        Log.d("로그인 성공", rb.toString())

                    }
                    else {
//                        응답 실패 (로그인 로직 실패)

                        Log.d("실패", "fail")

                       val errorBodyStr = response.errorBody()!!.string()
                        val jsonObj = JSONObject(errorBodyStr)
                        val message = jsonObj.getString("message")

                        Log.d("로그인 에러", message)
                    }
                }

                override fun onFailure(call: Call<JSONObject>, t: Throwable) {
//                   어떠한 response도 없을 떄(응답 없음) > 실제적으로 인터넷 연결 불량
                    Log. d("실패", "fail123")
                }
            })
//            val myIntent = Intent(mContext, MainActivity::class.java)
//            startActivity(myIntent)
//            finish()
        }
    }

    override fun setValues() {

    }

}