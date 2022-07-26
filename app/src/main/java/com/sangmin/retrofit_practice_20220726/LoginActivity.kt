package com.sangmin.retrofit_practice_20220726

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sangmin.retrofit_practice_20220726.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {

    lateinit var mBinding : ActivityLoginBinding


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
            val myIntent = Intent(mContext, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }

    override fun setValues() {

    }

}