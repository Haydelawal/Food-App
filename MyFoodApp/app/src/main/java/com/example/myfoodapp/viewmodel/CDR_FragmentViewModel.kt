package com.example.myfoodapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoodapp.data.model.doublerecycler.Category
import com.example.myfoodapp.data.model.doublerecycler.CategoryDRList
import com.example.myfoodapp.data.model.popularmeals.CategoryList
import com.example.myfoodapp.data.model.popularmeals.CategoryPopularMeals
import com.example.myfoodapp.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CDR_FragmentViewModel @Inject constructor(private val mealRepository: MealRepository) : ViewModel() {

    val mealsLiveDataDRFragment = MutableLiveData<List<CategoryPopularMeals>>()

    fun getMealsByCategoryDRFragment(categoryName: String) {
        mealRepository.getMealsByCategoryDRFrag(categoryName).enqueue(object: Callback<CategoryList>{
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                response.body()?.let { mealList ->
                    mealsLiveDataDRFragment.postValue(mealList.meals)

                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("CDR Frag", t.message.toString())
            }
        })
    }

    fun observeMealsDRFLiveData(): LiveData<List<CategoryPopularMeals>>{
        return mealsLiveDataDRFragment
    }
}