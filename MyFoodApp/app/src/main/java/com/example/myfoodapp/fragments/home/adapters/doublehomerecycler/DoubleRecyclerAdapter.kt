package com.example.myfoodapp.fragments.home.adapters.doublehomerecycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfoodapp.data.model.doublerecycler.Category
import com.example.myfoodapp.databinding.CategoryCardBinding

class DoubleRecyclerAdapter(val listenerDR: ClickListenerDR, val onLongItemClickDR: OnLongItemClickDR) :
    RecyclerView.Adapter<DoubleRecyclerAdapter.DRMealViewHolder>() {

    private var mealsList = ArrayList<Category>()

    fun setCategoryDRList(mealsList: List<Category>) {
        this.mealsList = mealsList as ArrayList<Category>
        notifyDataSetChanged()
    }

    class DRMealViewHolder(val binding: CategoryCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DRMealViewHolder {
        return DRMealViewHolder(CategoryCardBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DRMealViewHolder, position: Int) {

        val category = mealsList[position]
        val currentPosition = position

        holder.binding.apply {
            Glide.with(holder.itemView)
                .load(mealsList[position].strCategoryThumb)
                .into(imgCategory)

            tvCategoryName.text = category.strCategory
        }

        holder.itemView.setOnClickListener {
            listenerDR.onMyItemClick(category = category)
        }

        holder.itemView.setOnLongClickListener(object : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                onLongItemClickDR.onItemLongClick(mealsList[currentPosition])
                return true
            }

        })


    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}


interface ClickListenerDR {
    // item on click listener
    fun onMyItemClick(category: Category)
}

interface OnLongItemClickDR{
    fun onItemLongClick(category: Category)
}