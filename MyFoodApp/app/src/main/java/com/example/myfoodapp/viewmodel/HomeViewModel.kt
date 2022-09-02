package com.example.myfoodapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfoodapp.data.model.doublerecycler.Category
import com.example.myfoodapp.data.model.doublerecycler.CategoryDRList
import com.example.myfoodapp.data.model.randommeal.Meal
import com.example.myfoodapp.data.model.randommeal.MealList
import com.example.myfoodapp.data.model.popularmeals.CategoryList
import com.example.myfoodapp.data.model.popularmeals.CategoryPopularMeals
import com.example.myfoodapp.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor( private val mealRepository: MealRepository) : ViewModel() {

    private var randomMealLiveData = MutableLiveData<Meal>()

    private var mealDetailsLiveData = MutableLiveData<Meal>()

    fun observeRandomMealLiveData(): LiveData<Meal> {
        return randomMealLiveData
    }

    fun observeGetMealInfoLiveData(): LiveData<Meal> {
        return mealDetailsLiveData
    }

    val getRandomMeal = mealRepository.readData()
        .enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    val randomMeal: Meal = response!!.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal

                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })

    // popular (sea food recycler) stuff

    private var popularItemsLiveData = MutableLiveData<List<CategoryPopularMeals>>()


    fun observePopularMealLiveData(): LiveData<List<CategoryPopularMeals>> {
        return popularItemsLiveData
    }

    val getPopularItems = mealRepository.getPopularItems("Seafood")
        .enqueue(object : Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                if (response.body() != null) {
//                    val categoryMeal: CategoryPopularMeals = response!!.body()!!.meals[0]
                    popularItemsLiveData.value = response.body()!!.meals
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })


    fun getMealDetails(id: Int){
        mealRepository.getMealDetails(id)
            .enqueue(object : Callback<MealList>{
                override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                    if (response.body() != null) {
                        mealDetailsLiveData.value = response.body()!!.meals[0]
                    }
                }

                override fun onFailure(call: Call<MealList>, t: Throwable) {
                    Log.d("mealDetails", t.message.toString())
                }
            })
    }




    /// Double Recycler View Stuff

    private var mealDRGetCategories = MutableLiveData<List<Category>>()

    fun observeDRGetCategoriesLiveData(): LiveData<List<Category>> {
        return mealDRGetCategories
    }

    val getDRCategories = mealRepository.getDRCategories()
        .enqueue(object : Callback<CategoryDRList>{
            override fun onResponse(
                call: Call<CategoryDRList>,
                response: Response<CategoryDRList>
            ) {
                response.body()?.let { categoryDRList ->
                    mealDRGetCategories.postValue(categoryDRList.categories)
                }
            }

            override fun onFailure(call: Call<CategoryDRList>, t: Throwable) {
                Log.d("DR Fragment", t.message.toString())
            }
        })

    // categories dr fragment


}



