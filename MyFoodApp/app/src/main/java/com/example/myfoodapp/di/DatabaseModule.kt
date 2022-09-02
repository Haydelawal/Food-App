package com.example.myfoodapp.di

import android.content.Context
import androidx.room.Room
import com.example.myfoodapp.db.MealDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


    @Module
    @InstallIn(SingletonComponent::class)
    object DatabaseModule {

        @Singleton
        @Provides
        fun provideDatabase(
            @ApplicationContext context: Context
        ) = Room.databaseBuilder(
            context,
            MealDatabase::class.java,
            "person_database"
        ).build()

        @Singleton
        @Provides
        fun provideDao(database: MealDatabase) = database.mealDao()
    }
        
