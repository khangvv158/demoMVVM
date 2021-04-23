package com.sun.demomvvm.data.source.api

import com.sun.demomvvm.data.model.Pagination
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {

    @GET("users")
    suspend fun getUsers(@Query("page") numberPage: Int): Response<Pagination>
}
