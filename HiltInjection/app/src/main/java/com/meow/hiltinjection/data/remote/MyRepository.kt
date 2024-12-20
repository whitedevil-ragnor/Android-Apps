package com.meow.hiltinjection.data.remote

interface MyRepository {
    suspend fun doNetworkCall()
}