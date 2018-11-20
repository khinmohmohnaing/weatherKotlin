package com.example.dell.weatherappkotlin.view.views

import android.content.Context
import java.security.AccessControlContext

interface  Mainview {
    fun showProgress()
    fun hideProgress()
    fun getWeatherInfo()
    fun checkconnection(context: Context):Boolean
    fun showSnackBar()
    fun gotoCityWeatherInfo()

}