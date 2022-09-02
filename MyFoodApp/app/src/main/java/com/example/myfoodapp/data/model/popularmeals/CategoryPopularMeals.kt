package com.example.myfoodapp.data.model.popularmeals

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryPopularMeals(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
    ) : Parcelable
