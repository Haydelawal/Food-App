package com.example.myfoodapp.fragments.home.adapters.favefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfoodapp.data.model.randommeal.Meal
import com.example.myfoodapp.databinding.MealCardBinding



class FavFragmentAdapter(
    private val listener: ClickListener
) : RecyclerView.Adapter<FavFragmentAdapter.MyViewHolder>() {

    private var oldMealList = emptyList<Meal>()

    inner class MyViewHolder(val binding: MealCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MealCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = oldMealList[position]

        holder.binding.apply {
            Glide.with(holder.itemView)
                .load(currentItem.strMealThumb)
                .into(imgMeal)

            tvMealName.text = currentItem.strMeal
        }

        holder.itemView.setOnClickListener {
            listener.onMyItemClick(currentItem)
        }

    }



    override fun getItemCount(): Int {
        return oldMealList.size
    }

    fun setData(newMealList: List<Meal>){
        // diff util
        val diffUtil = MyDiffUtil(oldMealList, newMealList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldMealList = newMealList
        diffResults.dispatchUpdatesTo(this)
    }

    interface ClickListener {

        // item on click listener
        fun onMyItemClick(meal: Meal)
    }


    // then in my wherever its needed fragment
    // class ListFragment : Fragment(), MyAdapter.MyClickListener,

//    override fun onMyItemClick(person: Person) {
//        val selectedPerson = person
//
//        val action = ListFragmentDirections.actionListFragmentToEditFragment(selectedPerson!!)
//        findNavController().navigate(action)
//    }

}