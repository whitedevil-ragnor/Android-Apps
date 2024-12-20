package com.meow.weatherapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Instance {
    private const val BASE_URL="https://api.weatherapi.com"
    fun getInstance():Retrofit{
       return Retrofit.Builder().baseUrl(BASE_URL)
           .addConverterFactory(GsonConverterFactory.create()).build()
    }
    val WeatherApi:ApiService= getInstance().create(ApiService::class.java)
}