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
import com.example.dell.weatherappkotlin.interactor.MainIntearactor
import com.example.dell.weatherappkotlin.model.root
import com.example.dell.weatherappkotlin.presenter.MainPresenter
import com.example.dell.weatherappkotlin.view.adapter.MyAdapter
import com.example.dell.weatherappkotlin.view.base.BaseVH.onclick
import com.example.dell.weatherappkotlin.view.views.Mainview
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() ,Mainview, onclick {


    var isConnected = false

    lateinit var api: mWeatherApiInterface

    lateinit var presenter:MainPresenter
    lateinit var myadapter:MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        api = Get_Retrofit.getRetrofit().create(mWeatherApiInterface::class.java)
        weather_recycler.layoutManager = LinearLayoutManager(this)
        myadapter= MyAdapter(this)
        presenter = MainPresenter(this, MainIntearactor())
        isConnected=checkconnection(applicationContext)
        Log.i("isConnected",isConnected.toString())
        presenter.getInfo(isConnected)
        cityweatherbtn.setOnClickListener{presenter.gotoCity()}
    }
    override fun getWeatherInfo() {
        showProgress()
        val information = api.getweatherinfo(16.871311, 96.199379)

        information.enqueue(object : Callback<root> {
            override fun onResponse(call: Call<root>, response: Response<root>) {
                if (response.isSuccessful()) {
                    hideProgress()
                    myadapter.addList(response.body()!!)
                    weather_recycler.adapter = myadapter
                    myadapter.notifyDataSetChanged()
                    myadapter.setOnClick(this@MainActivity)
                } else
                    Log.e("MainActivity", "unsuccess")
            }

            override fun onFailure(call: Call<root>, t: Throwable) {
                Log.e("MainActivity", "fail")
            }

        })
    }
    override fun onItemClick(position: Int) {
        var infoList=myadapter.getList(position)
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra("weather", infoList.weather.get(0).description.toString())
        intent.putExtra("clouds", infoList.clouds.all.toString())
        intent.putExtra("wind", infoList.wind.speed.toString())
        intent.putExtra("date", infoList.dt_txt)
        intent.putExtra("image", "http://api.openweathermap.org/img/w/" +
                infoList.weather.get(0).icon)
        startActivity(intent)
    }
    override fun checkconnection(context: Context): Boolean {
        val ConnectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = ConnectionManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected ) {
            isConnected = true
        }

        return isConnected
    }
    override fun showProgress() {
        progress.visibility= View.VISIBLE
    }
    override fun hideProgress() {
        progress.visibility=View.GONE
    }
    override fun showSnackBar() {
        val snackbar = Snackbar.make(mainlayout,
            "Check your connection", Snackbar.LENGTH_INDEFINITE)
            .setAction("Try Again",
                {isConnected=checkconnection(applicationContext)
                presenter.getInfo(isConnected)} )
        snackbar.show()
    }

    override fun gotoCityWeatherInfo() {
        val intent:Intent= Intent(applicationContext,CityWeatherActivity::class.java)
        startActivity(intent)
    }

}
