package com.example.dell.weatherappkotlin.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Get_Retrofit {
    companion object {
        fun getRetrofit(): Retrofit {
            val client = OkHttpClient.Builder()
            val builder = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
            return builder.client(client.build()).build()
        }
    }
}