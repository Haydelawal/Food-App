package com.example.myfoodapp.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myfoodapp.data.model.doublerecycler.Category
import com.example.myfoodapp.data.model.popularmeals.CategoryPopularMeals
import com.example.myfoodapp.databinding.FragmentHomeBinding
import com.example.myfoodapp.fragments.home.adapters.doublehomerecycler.ClickListenerDR
import com.example.myfoodapp.fragments.home.adapters.doublehomerecycler.DoubleRecyclerAdapter
import com.example.myfoodapp.fragments.home.adapters.doublehomerecycler.OnLongItemClickDR
import com.example.myfoodapp.fragments.home.adapters.popularhome.ClickListener
import com.example.myfoodapp.fragments.home.adapters.popularhome.MostPopularRecyclerAdapter
import com.example.myfoodapp.fragments.home.adapters.popularhome.OnLongItemClick
import com.example.myfoodapp.viewmodel.HomeViewModel

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), ClickListener, OnLongItemClick, ClickListenerDR,
    OnLongItemClickDR {

    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding!!

    private val myViewModel: HomeViewModel by viewModels()
    private val myPopularAdapter: MostPopularRecyclerAdapter by lazy {
        MostPopularRecyclerAdapter(
            this,
            this
        )
    }
    private val myDRCategoryAdapter: DoubleRecyclerAdapter by lazy {
        DoubleRecyclerAdapter(
            this,
            this
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // random food single image
        myViewModel.observeRandomMealLiveData()
        observerRandomMeal()

        // popular food images
        myViewModel.observePopularMealLiveData()
        observerPopularMeal()
        popularItemsRecyclerView()

        // double recycler view
        myViewModel.observeDRGetCategoriesLiveData()
        observeDRCategoriesLivaData()
        recyclerViewDR()
        // double recycler///


        return binding.root
    }



    // double recycler///
    private fun recyclerViewDR() {
        binding.recyclerViewCategoriesDr.apply {
            layoutManager = GridLayoutManager(
                requireContext(), 3, GridLayoutManager.VERTICAL, false
            )
            adapter = myDRCategoryAdapter
        }
    }

    override fun onMyItemClick(category: Category) {
        // DR onMyItemClick
        val action = HomeFragmentDirections.homeFragmentToCategoryDRFragment(category)
        findNavController().navigate(action)
    }

    override fun onItemLongClick(category: Category) {
        // DR onItemLongClick

        Toast.makeText(requireContext(), "bbbb", Toast.LENGTH_SHORT).show()
    }

    private fun observeDRCategoriesLivaData() {
        myViewModel.observeDRGetCategoriesLiveData()
            .observe(viewLifecycleOwner, Observer { categories ->
                myDRCategoryAdapter.setCategoryDRList(categories)
            })
    }
    // double recycler///


    private fun popularItemsRecyclerView() {
        binding.recViewMealsPopular.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = myPopularAdapter
        }
    }

    override fun onMyItemClick(categoryPopularMeals: CategoryPopularMeals) {

        // over popular items on click

        val action = HomeFragmentDirections.homeToSea(categoryPopularMeals)
        findNavController().navigate(action)

    }

    override fun onItemLongClick(categoryPopularMeals: CategoryPopularMeals) {

    }


    private fun observerPopularMeal() {
        myViewModel.observePopularMealLiveData().observe(
            viewLifecycleOwner
        ) { mealList ->
            myPopularAdapter.setMealList(mealList)
        }
    }


    private fun observerRandomMeal() {
        myViewModel.observeRandomMealLiveData().observe(
            viewLifecycleOwner
        ) { meal ->
            Glide.with(this@HomeFragment)
                .load(meal!!.strMealThumb)
                .into(binding.imgRandomMeal)

            val selectedMeal = meal!!

            binding.imgRandomMeal.setOnClickListener {

//                val action = HomeFragmentDirections.homeToRandom(selectedMeal)
//                findNavController().navigate(action)
//
//
//                val action = HomeFragmentDirections.actionHomeFragmentToFavoritesFragment()
//                findNavController().navigate(action)

                val action = HomeFragmentDirections.actionHomeFragmentToCategoriesFragment()
                findNavController().navigate(action)
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }


}