package com.meow.weatherapp.retrofit

data class WeatherResponse(
    val current: Current,
    val location: Location
)