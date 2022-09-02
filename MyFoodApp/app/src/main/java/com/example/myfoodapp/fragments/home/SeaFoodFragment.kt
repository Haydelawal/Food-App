package com.example.myfoodapp.fragments.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myfoodapp.R
import com.example.myfoodapp.databinding.FragmentSeaFoodBinding
import com.example.myfoodapp.viewmodel.HomeViewModel
import com.example.myfoodapp.viewmodel.MealViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeaFoodFragment : Fragment() {

    private lateinit var _binding: FragmentSeaFoodBinding
    private val binding get() = _binding!!

    private val myViewModel: HomeViewModel by viewModels()
    private val mealViewModel: MealViewModel by viewModels()


    private val args: SeaFoodFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSeaFoodBinding.inflate(inflater, container, false)

        myViewModel.getMealDetails(args.seaFood.idMeal.toInt())
        observeMealDetailsLiveData()

        Glide.with(requireContext())
            .load(args.seaFood.strMealThumb)
            .into(binding.imgMealDetail)


        binding.collapsingToolbar.apply {
            title = args.seaFood.strMeal
            setCollapsedTitleTextColor(resources.getColor(R.color.white))
            setExpandedTitleColor(resources.getColor(R.color.white))
        }


        return binding.root
    }

    private fun observeMealDetailsLiveData() {
        myViewModel.observeGetMealInfoLiveData().observe(
            viewLifecycleOwner
        ) { mealList ->

            binding.tvCategoryInfo.apply {
                text = "Category: ${mealList.strCategory}"

            }
            binding.tvAreaInfo.text = "Area: ${mealList.strArea}"

            binding.tvContent.text = mealList.strInstructions

            binding.imgYoutube.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mealList.strYoutube))
                startActivity(intent)
            }

            // save
            binding.btnSave.setOnClickListener {
                mealViewModel.insertMeal(mealList!!)
                Toast.makeText(requireContext(), "Successfully Added To Favorites", Toast.LENGTH_SHORT).show()
            }
        }
    }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding.root
        }
    }
