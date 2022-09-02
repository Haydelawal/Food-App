package com.example.myfoodapp.di

import com.example.myfoodapp.Constants.BASE_URL
import com.example.myfoodapp.api.MealApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object RetrofitInstance {
//
//    val api: MealApi by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(MealApi::class.java)
//    }
//}