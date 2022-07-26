package com.sangmin.retrofit_practice_20220726.api


import com.sangmin.retrofit_practice_20220726.datas.BaseResponse
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
    ) : Call<JSONObject>




}