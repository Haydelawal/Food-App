package com.example.myfoodapp.fragments.home.adapters.popularhome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfoodapp.data.model.popularmeals.CategoryPopularMeals
import com.example.myfoodapp.databinding.MostPopularCardBinding

class MostPopularRecyclerAdapter(val listener: ClickListener, val onLongItemClick: OnLongItemClick) :
    RecyclerView.Adapter<MostPopularRecyclerAdapter.MostPopularMealViewHolder>() {

    private var mealsList: List<CategoryPopularMeals> = ArrayList()

    fun setMealList(mealsList: List<CategoryPopularMeals>) {
        this.mealsList = mealsList
        notifyDataSetChanged()
    }

    class MostPopularMealViewHolder(val binding: MostPopularCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularMealViewHolder {
        return MostPopularMealViewHolder(MostPopularCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MostPopularMealViewHolder, position: Int) {

        val categoryPopularMeals = mealsList[position]
        val currentPosition = position

        holder.binding.apply {
            Glide.with(holder.itemView)
                .load(mealsList[position].strMealThumb)
                .into(imgPopularMeal)

        }

        holder.itemView.setOnClickListener {
            listener.onMyItemClick(categoryPopularMeals = categoryPopularMeals)
        }

        holder.itemView.setOnLongClickListener(object : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                onLongItemClick.onItemLongClick(mealsList[currentPosition])
                return true
            }

        })


    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}


interface ClickListener {
    // item on click listener
    fun onMyItemClick(categoryPopularMeals: CategoryPopularMeals)
}

interface OnLongItemClick{
    fun onItemLongClick(categoryPopularMeals: CategoryPopularMeals)
}


