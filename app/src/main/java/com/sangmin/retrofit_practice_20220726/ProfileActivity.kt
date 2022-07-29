package com.sangmin.retrofit_practice_20220726

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.sangmin.retrofit_practice_20220726.databinding.ActivityProfileBinding
import com.sangmin.retrofit_practice_20220726.databinding.ActivitySignUpBinding
import com.sangmin.retrofit_practice_20220726.utils.GlobalData

class ProfileActivity : BaseActivity() {

    lateinit var mBinding: ActivityProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_profile)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {

//        닉네임 변경 버튼 클릭 이벤트 처리
        mBinding.changeNicBtn.setOnClickListener {
            if (mBinding.changeNickEdt.visibility == View.GONE) {
                mBinding.changeNickEdt.visibility = View.VISIBLE
                mBinding.changeNicBtn.text = "변경 취소"
            }else{
                mBinding.changeNickEdt.visibility = View.GONE
                mBinding.changeNicBtn.text = "닉네임 변경"
            }


        }

//      비밀번호 변경 에딧텍스트 이벤트 처리리
        mBinding.changePwEdt.addTextChangedListener {
            Log.d("현재 작성 중", it.toString())
            if(!it.toString().isBlank()) {
                mBinding.currentPwEdt.visibility = View.VISIBLE

            } else {
                mBinding.currentPwEdt.visibility = View.GONE
            }
        }

//        위에 작성된 변경내용 서버에 전달하는 로직 작성성


//        회원 탈퇴 로직 작성



    }
    override fun setValues() {
        mBinding.currentNickTxt.text = GlobalData.loginUser?.nick_name



    }
}