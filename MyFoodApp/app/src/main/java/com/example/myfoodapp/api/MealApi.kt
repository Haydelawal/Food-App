package com.example.myfoodapp.api

import com.example.myfoodapp.data.model.doublerecycler.CategoryDRList
import com.example.myfoodapp.data.model.randommeal.MealList
import com.example.myfoodapp.data.model.popularmeals.CategoryList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi  {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>
//
    @GET("lookup.php")
    fun getMealDetails(@Query("i") id:Int) : Call<MealList>

    @GET("filter.php")
    fun getPopularItems(@Query("c") categoryName: String) : Call<CategoryList>

    @GET("categories.php")
    fun getDRCategories(): Call<CategoryDRList>

    @GET("filter.php")
    fun getMealsByCategoryDRFrag(@Query("c") categoryName: String) : Call<CategoryList>
}