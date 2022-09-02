package com.example.paging3_newsapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.paging3_newsapp.utils.Constants.NEWS_TABLE

import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = NEWS_TABLE)
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    @PrimaryKey
    val title: String,
    val url: String?,
    val urlToImage: String?
) : Parcelable
