package com.example.myfoodapp.data.model.randommeal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealList(
    val meals: List<Meal>
): Parcelable