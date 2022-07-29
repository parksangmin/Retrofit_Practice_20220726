package com.sangmin.retrofit_practice_20220726

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.sangmin.retrofit_practice_20220726.databinding.ActivitySignUpBinding
import com.sangmin.retrofit_practice_20220726.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var mBinding : ActivitySignUpBinding


    var emailOk = false
    var nickOk = false

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
            checkValue("EMAIL", inputEmail)


//            apiList.getRequestCheckValue("EMAIL", inputEmail)
//                .enqueue(object : Callback<BasicResponse>{
//                    override fun onResponse(
//                        call: Call<BasicResponse>,
//                        response: Response<BasicResponse>
//                    ) {
//                        if (response.isSuccessful) {
//                            val br = response.body()!!
//                            Log.d("응답", br.toString())
//                            mBinding.checkEmailTxt.text = br.message
//                            Toast.makeText(mContext, br.message, Toast.LENGTH_SHORT).show()
//                            emailOk = true
//
//                        }
//                        else {
//                            val errorBodyStr = response.errorBody()!!.string()
//                            val jsonObj = JSONObject(errorBodyStr)
//                            val message = jsonObj.getString("message")
//                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
//                            mBinding.checkEmailTxt.text ="중복 검사를 해주세요"
//                            emailOk = false
//
//                        }
//
//                    }
//
//                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
//
//                    }
//                })



        }

//        닉네임 중복 확인버튼 클릭 이벤트 처리
        mBinding.checkNickBtn.setOnClickListener {
            val inputNick = mBinding.nickEdt.text.toString()
            checkValue("NICK_NAME", inputNick)
//            apiList.getRequestCheckValue("NICK_NAME", inputNick)
//                .enqueue(object : Callback<BasicResponse>{
//                    override fun onResponse(
//                        call: Call<BasicResponse>,
//                        response: Response<BasicResponse>
//                    ) {
//                        if(response.isSuccessful){
////                            성공 된 값
//                            val br = response.body()!!
//                            mBinding.checkNickTxt.text = br.message
//                            Toast.makeText(mContext, br.message, Toast.LENGTH_SHORT).show()
//                            nickOk = true
//                        }
//                        else {
////                            errorBody를 Json Parsing
//                            val errorBodyStr = response.errorBody()!!.string()
//                            val jsonObj = JSONObject(errorBodyStr)
//                            val message = jsonObj.getString("message")
//
//                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
//                            mBinding.checkNickTxt.text = "중복 검사를 해주세요"
//                            nickOk = false
//                        }
//
//                    }
//
//                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
//
//
//                    }
//                })
        }

//        회원 가입 로직
        mBinding.signUpBtn.setOnClickListener {
//            1. 이메일 중복 체크를 했는가? (맴버변수로 Boolean 값 - emailOk = true / false)
            if(!emailOk) {
                Toast.makeText(mContext, "이메일 중복체크를 해주세요", Toast.LENGTH_SHORT).show()
//                setOnClickListener 아래 코드를 스킵하고 탈출하는 코드(JAVA - Break)
                return@setOnClickListener
            }

//            2. 닉네임 중복 체크를 했는가?
            if(!nickOk){
                Toast.makeText(mContext, "닉네임 중복체크를 해주세요 ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            3. 비밀번호를 입력하였는가?
            val inputPw = mBinding.passwordEdt.text.toString()
//            isEmpty : String 값이 비어있는가? (공백 인정)
//            isBlank : String 값이 비어있는가? (공백 인정 x)
            if (inputPw.isBlank()) {
//                비밀번호가 공백 값이거나 or 빈 값
                Toast.makeText(mContext, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                Toast.makeText(mContext, "비밀번호는 공백일 수 없습니다", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            회원가입 로직 실행
            val inputEmail = mBinding.emailEdt.text.toString()
            val inputNick = mBinding.nickEdt.text.toString()
            apiList.getRequestSignUp(inputEmail, inputPw, inputNick)
                .enqueue(object : Callback<BasicResponse>{
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {
                        if (response.isSuccessful) {
                            val br = response.body()!!
                            val nickname = br.data.user.nick_name
                            Toast.makeText(mContext, "${nickname}님 가입을 환영합니다", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                    }
                })

        }

        backBtn.setOnClickListener {
            finish()
        }
    }
    override fun setValues() {

        titleTxt.text = "회원가입"
        backBtn.visibility = View.VISIBLE

    }
//[도전과제]
    fun checkValue(type : String, value : String) {
//        이메일과 닉네임을 동시에 중복 검사할 수 있는 로직으로 작성(생성자를 통새서 분류를 생각해 보자)
        apiList.getRequestCheckValue(type, value).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
//                생성자로 받아온 type에 따른 response 분기 처리
                if(response.isSuccessful){
//                    응답이 성공적으로 왔을 때 (code : 200)
                    val br = response.body()!!
                    if (type == "EMAIL"){
                        mBinding.checkNickTxt.text = br.message
                        emailOk = true
                    }else{
                        mBinding.checkNickTxt.text = br.message
                        nickOk = true

                    }

                }else {
//                    응답이 성공적이지 않을 때 (code : 200의 경우)
                    val errorBodyStr = response.errorBody()!!.string()
                    val jsonObj = JSONObject(errorBodyStr)
                    val message = jsonObj.getString("message")

                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                    if(type == "EMAIL") {
                        mBinding.checkEmailTxt.text = "중복 검사를 해주세요."
                        emailOk = false
                    }
                    else {
//                        type == "NICK_NAME"
                        mBinding.checkNickTxt.text = "중복 검사를 해주세요."
                        nickOk = false
                    }
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })

    }

}