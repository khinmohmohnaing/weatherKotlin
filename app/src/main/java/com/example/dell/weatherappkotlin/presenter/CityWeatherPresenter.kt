package com.example.dell.weatherappkotlin.presenter

import com.example.dell.weatherappkotlin.interactor.CityWeatherInteractor
import com.example.dell.weatherappkotlin.view.views.CityWeatherView

class CityWeatherPresenter(
    private val cityweatherview: CityWeatherView,
    private val cityWeatherInteractor: CityWeatherInteractor
):CityWeatherInteractor.cityweatherInterface {


    fun getCityWeatherInfo(connected: Boolean) {
        cityWeatherInteractor.getCityInfo(connected,this)
    }
    fun testResponse(isSuccess: Boolean) {
        cityWeatherInteractor.showMessage(this,isSuccess)
    }
    override fun showProgress() {
        cityweatherview.showProgress()
    }

    override fun hideProgress() {
        cityweatherview.hideProgress()
    }

    override fun getCityWeatherInfo() {
        cityweatherview.getCityWeatherInfo()
    }
    override fun showSnackBar() {
        cityweatherview.showSnackBar()
    }
    override fun showNotFoundMessage() {
        cityweatherview.showNotFoundMessage()
    }
    override fun showCityWeatherData() {
        cityweatherview.showCityWeatherData()
    }

}