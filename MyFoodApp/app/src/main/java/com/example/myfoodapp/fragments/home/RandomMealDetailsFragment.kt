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
import com.example.myfoodapp.databinding.FragmentRandomMealDetailsBinding
import com.example.myfoodapp.viewmodel.HomeViewModel
import com.example.myfoodapp.viewmodel.MealViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomMealDetailsFragment : Fragment() {

    private lateinit var _binding: FragmentRandomMealDetailsBinding
    private val binding get() = _binding!!

    private val mealViewModel: MealViewModel by viewModels()

    private val args: RandomMealDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRandomMealDetailsBinding.inflate(inflater, container, false)


        Glide.with(requireContext())
            .load(args.randomMeal.strMealThumb)
            .into(binding.imgMealDetail)

        binding.tvCategoryInfo.text = "Category: ${args.randomMeal.strCategory}"

        binding.tvAreaInfo.text = "Area: ${args.randomMeal.strArea}"

            binding.collapsingToolbar.apply {
                title = args.randomMeal.strMeal
                setCollapsedTitleTextColor(resources.getColor(R.color.white))
                setExpandedTitleColor(resources.getColor(R.color.white))
            }

        binding.tvContent.text = args.randomMeal.strInstructions

        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(args.randomMeal.strYoutube))
            startActivity(intent)
        }

        //onLikeImageClick
        binding.btnSave.setOnClickListener {
            mealViewModel.insertMeal(args.randomMeal!!)
            Toast.makeText(requireContext(), "Successfully Added To Favorites", Toast.LENGTH_SHORT).show()
        }

//        ///
//        onLikeImageClick()

        return binding.root
    }

//    private fun onLikeImageClick() {
//        binding.imgYoutube.setOnClickListener {
//            mealViewModel.insertMeal()
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}