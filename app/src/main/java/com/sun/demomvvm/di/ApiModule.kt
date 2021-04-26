package com.sun.demomvvm.di

import com.sun.demomvvm.data.source.RetrofitClient
import com.sun.demomvvm.data.source.api.ServiceAPI
import org.koin.dsl.module

val apiModule = module {
    single { RetrofitClient.create(ServiceAPI::class.java) }
}
