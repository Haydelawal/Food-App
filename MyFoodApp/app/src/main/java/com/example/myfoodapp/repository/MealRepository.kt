package com.example.myfoodapp.repository

import com.example.myfoodapp.api.MealApi
import com.example.myfoodapp.data.model.doublerecycler.CategoryDRList
import com.example.myfoodapp.data.model.randommeal.MealList
import com.example.myfoodapp.data.model.popularmeals.CategoryList
import retrofit2.Call
import javax.inject.Inject

class MealRepository @Inject
constructor(
    private val mealApi: MealApi
) {

    fun readData(): Call<MealList> {
        return mealApi.getRandomMeal()
    }

    fun getMealDetails(id: Int): Call<MealList> {
        return mealApi.getMealDetails(id)
    }

    fun getPopularItems(categoryName: String): Call<CategoryList>{
        return mealApi.getPopularItems(categoryName)
    }

    fun getDRCategories(): Call<CategoryDRList>{
        return mealApi.getDRCategories()
    }


    fun getMealsByCategoryDRFrag(categoryName: String): Call<CategoryList>{
        return mealApi.getMealsByCategoryDRFrag(categoryName)
    }
}