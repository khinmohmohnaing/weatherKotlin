package com.example.dell.weatherappkotlin.presenter

import com.example.dell.weatherappkotlin.interactor.MainIntearactor
import com.example.dell.weatherappkotlin.view.views.Mainview

class MainPresenter(
    private var mainview: Mainview,
    private var mainIntearactor: MainIntearactor
):MainIntearactor.detectInterface {


    fun getInfo(connected: Boolean) {
        mainIntearactor.callWeatherInfo(connected,this)
    }
    fun gotoCity(){
        mainIntearactor.gotoCityInfo(this)
    }
    override fun showProgress() {
        mainview.showProgress()
    }

    override fun hideProgress() {
        mainview.hideProgress()
    }

    override fun showSnackBar() {
        mainview.showSnackBar()
    }
    override fun getWeatherInfo() {
        mainview.getWeatherInfo()
    }
    override fun gotoCityInfo() {
        mainview.gotoCityWeatherInfo()
    }

}