package com.example.dell.weatherappkotlin.view.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.dell.weatherappkotlin.R
import com.example.dell.weatherappkotlin.api.Get_Retrofit
import com.example.dell.weatherappkotlin.api.mWeatherApiInterface
import com.example.dell.weatherappkotlin.interactor.CityWeatherInteractor
import com.example.dell.weatherappkotlin.model.root
import com.example.dell.weatherappkotlin.presenter.CityWeatherPresenter
import com.example.dell.weatherappkotlin.view.adapter.MyAdapter
import com.example.dell.weatherappkotlin.view.base.BaseVH
import com.example.dell.weatherappkotlin.view.views.CityWeatherView
import kotlinx.android.synthetic.main.activity_city_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityWeatherActivity : AppCompatActivity(),CityWeatherView,BaseVH.onclick {

    var isConnected = false
    lateinit var api: mWeatherApiInterface

    lateinit var presenter: CityWeatherPresenter
    lateinit var myadapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_weather)
        cityWeatherInfoRecycler.layoutManager=LinearLayoutManager(this)
        api=Get_Retrofit.getRetrofit().create(mWeatherApiInterface::class.java)
        myadapter= MyAdapter(this)
        isConnected=checkconnection(this)
        presenter= CityWeatherPresenter(this, CityWeatherInteractor())
        okbtn.setOnClickListener {presenter.getCityWeatherInfo(isConnected)}
    }
    override fun showProgress() {
        progressbar.visibility= View.VISIBLE
    }

    override fun hideProgress() {
        progressbar.visibility=View.GONE
    }

    override fun getCityWeatherInfo() {
        showProgress()
        val cityWeatherInfo=api.getcityweatherinfo(cityedt.text.toString())
        cityWeatherInfo.enqueue(object : Callback<root>{
            override fun onFailure(call: Call<root>, t: Throwable) {
                Log.i("CityWeatherActivity","fail")
            }

            override fun onResponse(call: Call<root>, response: Response<root>) {
                if(response.isSuccessful){
                    hideProgress()
                    presenter.testResponse(true)
                    Log.i("CityWeatherActivity","success")
                    myadapter.addList(response.body()!!)
                    cityWeatherInfoRecycler.adapter=myadapter
                    myadapter.notifyDataSetChanged()
                    myadapter.setOnClick(this@CityWeatherActivity)
                }
                else{
                    hideProgress()
                    Log.i("CityWeatherActivity","unsucess")
                    presenter.testResponse(false)
                }
            }

        })

    }
    override fun checkconnection(context: Context): Boolean {
        val ConnectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = ConnectionManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected ) {
            isConnected = true
        }

        return isConnected
    }

    override fun showSnackBar() {
        val snackbar = Snackbar.make(cityLayout,
            "Check your connection", Snackbar.LENGTH_INDEFINITE)
            .setAction("Try Again",
                {isConnected=checkconnection(applicationContext)
                 presenter.getCityWeatherInfo(isConnected)} )
        snackbar.show()
    }

    override fun onItemClick(position: Int) {

        var infoList=myadapter.getList(position)
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra("weather", infoList.weather.get(0).description)
        intent.putExtra("clouds", infoList.clouds.all.toString())
        intent.putExtra("wind", infoList.wind.speed.toString())
        intent.putExtra("date", infoList.dt_txt)
        intent.putExtra("image", "http://api.openweathermap.org/img/w/" +
                infoList.weather.get(0).icon)
        startActivity(intent)
    }
    override fun showNotFoundMessage() {
        notfoundtxt.visibility=View.VISIBLE
        cityWeatherInfoRecycler.visibility=View.GONE
    }
    override fun showCityWeatherData() {
        notfoundtxt.visibility=View.GONE
        cityWeatherInfoRecycler.visibility=View.VISIBLE
    }

}
