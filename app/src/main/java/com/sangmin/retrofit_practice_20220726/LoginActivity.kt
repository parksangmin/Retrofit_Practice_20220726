package com.sangmin.retrofit_practice_20220726


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import org.json.JSONObject
import com.sangmin.retrofit_practice_20220726.databinding.ActivityLoginBinding
import com.sangmin.retrofit_practice_20220726.datas.BasicResponse
import com.sangmin.retrofit_practice_20220726.utils.ContextUtil
import retrofit2.*

class LoginActivity : BaseActivity() {

    lateinit var mBinding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {
        mBinding.loginBtn.setOnClickListener {
            val inputEmail = mBinding.emailEdt.text.toString()
            val inputPw = mBinding.passwordEdt.text.toString()

//            로그인 API 연결 로직
            apiList.getRequestLogin(inputEmail, inputPw).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
//           실제로 response가 도달했을 때
                    val responseStr = response.toString()
                    Log.d("로그인", responseStr)
                    if (response.isSuccessful) {
//                        응답이 성공 (로그인 로직 성공 - id / pw 일치)
                        val br = response.body()!!
                        Log.d("로그인 성공", br.toString())

//  로그인 토큰 저장
                        val token = br.data.token
                        ContextUtil.setLoginToken(mContext, token)

//                        자동 로그인 체크 여부 저장
                        ContextUtil.setAutoLogin(mContext, mBinding.autoLoginCb.isChecked)

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)
                        finish()


                    } else {
//                        응답 실패 (로그인 로직 실패)

                        Log.d("실패", "fail")

                        val errorBodyStr = response.errorBody()!!.string()
                        val jsonObj = JSONObject(errorBodyStr)
                        val message = jsonObj.getString("message")

                        Log.d("로그인 에러", message)
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
//                   어떠한 response도 없을 떄(응답 없음) > 실제적으로 인터넷 연결 불량
                    Log.d("실패", "fail123")
                }
            })

        }

        mBinding.signUpBtn.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }


    }

    override fun setValues() {
        mBinding.autoLoginCb.isChecked = ContextUtil.getAutoLogin(mContext)

    }

}