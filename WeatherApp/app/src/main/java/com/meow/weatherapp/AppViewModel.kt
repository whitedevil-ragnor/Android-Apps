package com.meow.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meow.weatherapp.retrofit.ApiService
import com.meow.weatherapp.retrofit.Constant
import com.meow.weatherapp.retrofit.Instance
import com.meow.weatherapp.retrofit.WeatherResponse
import kotlinx.coroutines.launch

class AppViewModel:ViewModel() {
    private val weatherApi=Instance.WeatherApi
    private val _weatherResponse= MutableLiveData<NetworkResponse<WeatherResponse>>()
    val weatherResponse:LiveData<NetworkResponse<WeatherResponse>> = _weatherResponse
    fun getData(city:String){
        _weatherResponse.value= NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response=weatherApi.getWeather(Constant.API_KEY,city)
                if (response.isSuccessful){
                    response.body()?.let {
                        _weatherResponse.value=NetworkResponse.Success(it)
                    }
                }else{
                   _weatherResponse.value=NetworkResponse.Error("Failed to Fetch")
                }
            }catch (e:Exception){
                _weatherResponse.value=NetworkResponse.Error("Failed to Fetch")
            }
        }
    }}