package com.example.myfoodapp.data.model.popularmeals

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class CategoryList(
    val meals: List<CategoryPopularMeals>
) : Parcelable
