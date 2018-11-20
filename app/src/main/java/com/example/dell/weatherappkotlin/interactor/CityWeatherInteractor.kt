package com.example.dell.weatherappkotlin.interactor

class CityWeatherInteractor {
    interface cityweatherInterface{
        fun showProgress()
        fun hideProgress()
        fun getCityWeatherInfo()
        fun showSnackBar()
        fun showNotFoundMessage()
        fun showCityWeatherData()
    }
    fun getCityInfo(isConnected:Boolean,cityweatherInterface: cityweatherInterface){
        if(isConnected==true){
            cityweatherInterface.hideProgress()
            cityweatherInterface.getCityWeatherInfo()
        }
        else{
            cityweatherInterface.showProgress()
            cityweatherInterface.showSnackBar()
        }
    }

    fun showMessage(
        cityweatherInterface: cityweatherInterface,
        isSuccess: Boolean
    ) {
        if(isSuccess){
            cityweatherInterface.showCityWeatherData()
        }else
        cityweatherInterface.showNotFoundMessage()
    }
}