package com.sangmin.retrofit_practice_20220726.api


import com.sangmin.retrofit_practice_20220726.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface APIList {
// main
//  토픽데이터 받아오기
    @GET("/main_info")
    fun getRequestMainInfo (
    @Header("X-Http-Token") token : String
    ) : Call<BasicResponse>

//    topic
//    세부 토픽 데이터 받아오기
    @GET("/topic/{topic_id}")
    fun getRequestDetailTopic (
    @Header("X-Http-Token") token : String,
    @Path ("topic_id") topicId: Int
    ) : Call<BasicResponse>




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


//    내 정보 조회
    @GET("/user_info")
    fun getRequestMyInfo (
    @Header("X-Http-Token") token : String
    ) : Call<BasicResponse>



}