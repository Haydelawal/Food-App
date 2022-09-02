package com.example.myfoodapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myfoodapp.data.model.randommeal.Meal

@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal)

    @Delete
    suspend fun deleteMeal(meal: Meal)

    @Query("SELECT * FROM mealInformation")
     fun getAllMeals(): LiveData<List<Meal>>

    @Query("DELETE FROM mealInformation")
    suspend fun deleteAllSavedMealData()
}