package com.example.myfoodapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myfoodapp.data.model.randommeal.Meal


@Database(
    entities = [Meal::class],
    version = 1,
    exportSchema = false
)
abstract class MealDatabase: RoomDatabase() {

    abstract fun mealDao(): MealDao

}