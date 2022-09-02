package com.example.myfoodapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfoodapp.api.MealApi
import com.example.myfoodapp.data.model.randommeal.Meal
import com.example.myfoodapp.data.model.randommeal.MealList
import com.example.myfoodapp.db.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val repository: MealRepository
//    private val mealApi: MealApi
    ) : ViewModel() {

//    private var mealDetailsLiveData =MutableLiveData<Meal>()

    val getAllMeals = repository.getAllMeals()

    fun insertMeal(meal: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMeal(meal)
        }
    }

    fun deleteMeal(meal: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMeal(meal)
        }
    }

    fun deleteAllSavedMealData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllSavedMealData()
        }
    }

//    fun getMealDetail(id: Int){
//        mealApi.getMealDetails(id).enqueue(object : Callback<MealList>{
//            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
//                if (response.body() != null) {
//                    mealDetailsLiveData.value = response.body()!!.meals[0]
//                }
//            }
//
//            override fun onFailure(call: Call<MealList>, t: Throwable) {
//               Log.d("Meal VM", t.message.toString())
//            }
//        })
//    }


}