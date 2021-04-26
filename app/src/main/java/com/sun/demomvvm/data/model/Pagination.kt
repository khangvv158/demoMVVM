package com.sun.demomvvm.data.model

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPage: Int,
    @SerializedName("data")
    val data: MutableList<User>
)
