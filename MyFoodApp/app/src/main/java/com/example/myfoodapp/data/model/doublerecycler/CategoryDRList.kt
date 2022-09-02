package com.example.myfoodapp.data.model.doublerecycler

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryDRList (
    val categories: List<Category>
        ) : Parcelable