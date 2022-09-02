package com.example.myfoodapp.fragments.home.adapters.categoriesdouble

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfoodapp.data.model.popularmeals.CategoryPopularMeals
import com.example.myfoodapp.databinding.MealCardBinding

class CDR_Fragment_Adapter (val listenerDRFragment: ClickListenerDRFragment, val onLongItemClickDRFragment: OnLongItemClickDRFragment) :
    RecyclerView.Adapter<CDR_Fragment_Adapter.CDR_FragmentViewHolder>() {

    private var mealsList: List<CategoryPopularMeals> = ArrayList()

    fun setMealList(mealsList: List<CategoryPopularMeals>) {
        this.mealsList = mealsList
        notifyDataSetChanged()
    }

    class CDR_FragmentViewHolder(val binding: MealCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CDR_FragmentViewHolder {
        return CDR_FragmentViewHolder(MealCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CDR_FragmentViewHolder, position: Int) {

        val category = mealsList[position]
        val currentPosition = position

        holder.binding.apply {
            Glide.with(holder.itemView)
                .load(mealsList[position].strMealThumb)
                .into(imgMeal)

            tvMealName.text = category.strMeal
        }

        holder.itemView.setOnClickListener {
            listenerDRFragment.onMyItemClick(categoryPopularMeals= category)
        }

        holder.itemView.setOnLongClickListener(object : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                onLongItemClickDRFragment.onItemLongClick(mealsList[currentPosition])
                return true
            }

        })


    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}


interface ClickListenerDRFragment {
    // item on click listener
    fun onMyItemClick(categoryPopularMeals: CategoryPopularMeals)
}

interface OnLongItemClickDRFragment{
    fun onItemLongClick(categoryPopularMeals: CategoryPopularMeals)
}