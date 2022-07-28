package com.sangmin.retrofit_practice_20220726

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : BaseActivity() {

    var isAutoLoginOk = false
    var isTokenOk = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {


    }

    override fun setValues() {
        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed(
            {

//            1. 실제로 autoLogin을 체크 했는가?
//            2. 가지고 있는 토큰이 유효한가?
            if(isAutoLoginOk && isTokenOk) {
//                autoLogin 체크 & 토큰 유효성 두 가지 모두 true > 자동로그인 성공
                val myIntent = Intent(mContext, MainActivity::class.java)
                startActivity(myIntent)
                finish()

            }else {
//                두가지 중 하나라도 false > 자동로그인 실패
                val myInten = Intent(mContext, LoginActivity::class.java)
                finish()

            }



        },2500)

    }
}