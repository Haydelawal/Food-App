package com.example.myfoodapp.db

import androidx.lifecycle.LiveData
import com.example.myfoodapp.data.model.randommeal.Meal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealRepository @Inject constructor
    (
    private val mealDao: MealDao
) {

    suspend fun insertMeal(meal: Meal) {
        return mealDao.insertMeal(meal)
    }

    suspend fun deleteMeal(meal: Meal) {
        return mealDao.deleteMeal(meal)
    }

    fun getAllMeals(): LiveData<List<Meal>> {
        return mealDao.getAllMeals()
    }


    suspend fun deleteAllSavedMealData() {
        mealDao.deleteAllSavedMealData()
    }
}