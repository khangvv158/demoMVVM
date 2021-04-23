package com.sun.demomvvm.di

import com.sun.demomvvm.data.repository.UserRepository
import com.sun.demomvvm.data.repository.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}
