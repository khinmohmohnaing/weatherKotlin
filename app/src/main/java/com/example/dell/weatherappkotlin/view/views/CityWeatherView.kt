package com.example.dell.weatherappkotlin.view.views

import android.content.Context

interface CityWeatherView {
    fun showProgress()
    fun hideProgress()
    fun getCityWeatherInfo()
    fun checkconnection(context: Context):Boolean
    fun showSnackBar()
    fun showNotFoundMessage()
    fun showCityWeatherData()
}