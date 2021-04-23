package com.sun.demomvvm.app

import android.app.Application
import com.sun.demomvvm.di.apiModule
import com.sun.demomvvm.di.repositoryModule
import com.sun.demomvvm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(listOf(
                apiModule,
                repositoryModule,
                viewModelModule))
        }
    }
}
