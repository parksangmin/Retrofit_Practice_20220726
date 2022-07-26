package com.sangmin.retrofit_practice_20220726.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerApi {

    companion object{

//        서버주소 맴버변수화
        val baseUrl : "http://54.180.52.26/"

//        retrofit 초기화 진행
        var retrofit : Retrofit? = null

        fun getRetrofit() : Retrofit {

            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }


            return retrofit!!

        }


    }
}