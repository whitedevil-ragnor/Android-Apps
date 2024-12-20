package com.meow.hiltinjection.domain.repository

import android.app.Application
import android.util.Log
import com.meow.hiltinjection.R
import com.meow.hiltinjection.data.remote.MyApi
import com.meow.hiltinjection.data.remote.MyRepository

class MyRepositoryImpl(
    myApi: MyApi,
    private val appContext:Application):MyRepository {
    init {
        val appName= appContext.getString(R.string.app_name)
        println("Hey my app name is $appName")
    }
    override suspend fun doNetworkCall() {
        TODO("Not yet implemented")
    }
}