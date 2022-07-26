package com.sangmin.retrofit_practice_20220726

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sangmin.retrofit_practice_20220726.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {

    lateinit var mBinding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }

}