package com.example.dell.weatherappkotlin.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.dell.weatherappkotlin.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val intent=intent
        detailweathers.text=intent.getStringExtra("weather")
        detailclouds.text=intent.getStringExtra("clouds")
        detailwinds.text=intent.getStringExtra("wind")
        detaildates.text=intent.getStringExtra("date")
        Glide.with(this).load(intent.getStringExtra("image")).into(detailimg)
    }
}
