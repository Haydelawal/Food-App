package com.example.myfoodapp.fragments.home.adapters.favefragment

import androidx.recyclerview.widget.DiffUtil
import com.example.myfoodapp.data.model.randommeal.Meal

class MyDiffUtil(
    private val oldList : List<Meal>,
    private val newList : List<Meal>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].idMeal == newList[newItemPosition].idMeal
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemPosition == newItemPosition

    }
}