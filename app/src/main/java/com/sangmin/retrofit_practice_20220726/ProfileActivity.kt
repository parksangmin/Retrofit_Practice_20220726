package com.sangmin.retrofit_practice_20220726

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sangmin.retrofit_practice_20220726.databinding.ActivityProfileBinding
import com.sangmin.retrofit_practice_20220726.databinding.ActivitySignUpBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }
}