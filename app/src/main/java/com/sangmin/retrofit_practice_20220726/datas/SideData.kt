package com.sangmin.retrofit_practice_20220726.datas

import java.io.Serializable

data class SideData(
    val id : Int,
    val topic_id : Int,
    val title : String,
    val vote_count : Int
) : Serializable
