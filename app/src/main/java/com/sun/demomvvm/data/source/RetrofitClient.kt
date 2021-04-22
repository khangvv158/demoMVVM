package com.sun.demomvvm.data.source

import com.sun.demomvvm.utils.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val CONNECT_TIMEOUT = 60L
    private const val READ_TIMEOUT = 30L

    private fun callAPI(): Retrofit {
        val builder = OkHttpClient
            .Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
        val client = builder.build()
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return callAPI().create(service)
    }
}
