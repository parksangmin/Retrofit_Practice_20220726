package com.sangmin.retrofit_practice_20220726.datas

data class DataResponse(
    val token : String,
    val user : UserData,
    val topics : ArrayList<TopicData>,
    val topic : TopicData
) {
}