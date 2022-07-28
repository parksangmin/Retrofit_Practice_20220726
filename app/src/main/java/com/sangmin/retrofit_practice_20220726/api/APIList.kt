package com.sangmin.retrofit_practice_20220726.api


import com.sangmin.retrofit_practice_20220726.datas.BaseResponse
import com.sangmin.retrofit_practice_20220726.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface APIList {

//    user
//    로그인
    @FormUrlEncoded
    @POST("/user")
    fun getRequestLogin (
    @Field("email") email : String,
    @Field("password") password : String
    ) : Call<BasicResponse>


//  회원가입
    @FormUrlEncoded
    @PUT("/user")
    fun getRequestSignUp (
    @Field("email") email: String,
    @Field("password") password: String,
    @Field("nick_name") nickname : String
    ) : Call<BasicResponse>


//    이메일 중복 체크
    @GET("/user_check")
    fun getRequestCheckValue (
    @Query("type") type : String,
    @Query("value") value : String,
    ) : Call<BasicResponse>




}