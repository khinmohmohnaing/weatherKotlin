package com.example.dell.weatherappkotlin.interactor

 class MainIntearactor {
     interface detectInterface{
         fun showProgress()
         fun hideProgress()
         fun showSnackBar()
         fun getWeatherInfo()
         fun gotoCityInfo()
     }
    fun callWeatherInfo(isConnected:Boolean,detectInterface: detectInterface){
        if (isConnected==true){
            detectInterface.getWeatherInfo()
            detectInterface.hideProgress()
        }
        else{
            detectInterface.showProgress()
            detectInterface.showSnackBar()
        }
    }

     fun gotoCityInfo(detectInterface: detectInterface){
         detectInterface.gotoCityInfo()
     }
 }