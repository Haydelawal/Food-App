package com.example.myfoodapp.fragments.cdr

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myfoodapp.data.model.popularmeals.CategoryPopularMeals
import com.example.myfoodapp.databinding.FragmentCategoryDRBinding
import com.example.myfoodapp.fragments.home.adapters.categoriesdouble.CDR_Fragment_Adapter
import com.example.myfoodapp.fragments.home.adapters.categoriesdouble.ClickListenerDRFragment
import com.example.myfoodapp.fragments.home.adapters.categoriesdouble.OnLongItemClickDRFragment
import com.example.myfoodapp.viewmodel.CDR_FragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDRFragment : Fragment(), ClickListenerDRFragment, OnLongItemClickDRFragment {

    private lateinit var _binding: FragmentCategoryDRBinding
    private val binding get() = _binding!!

    private val myViewModel: CDR_FragmentViewModel by viewModels()
    private val args: CategoryDRFragmentArgs by navArgs()
    private val cdrFragmentAdapter: CDR_Fragment_Adapter by lazy {
        CDR_Fragment_Adapter(this, this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCategoryDRBinding.inflate(inflater, container, false)

//        myViewModel.observeMealsDRFLiveData().observe(viewLifecycleOwner, Observer { mealList ->
//            mealList.forEach {
//                Log.d("testing", it.strMeal)
//            }
//        })

        myViewModel.getMealsByCategoryDRFragment(args.cdrItems.strCategory)

        myViewModel.observeMealsDRFLiveData().observe(viewLifecycleOwner, Observer { mealList ->
            cdrFragmentAdapter.setMealList(mealList)


        })

        Log.d("testing", args.cdrItems.strCategory)


//        observeMealDetailsLiveData()

//        Glide.with(requireContext())
//            .load(args.cdrItems.strCategoryThumb)
//            .into(binding.loadingGifMeals)
//
//
//        binding.collapsingToolbar.apply {
//            title = args.seaFood.strMeal
//            setCollapsedTitleTextColor(resources.getColor(R.color.white))
//            setExpandedTitleColor(resources.getColor(R.color.white))
//        }

        prepareRecyclerView()

        return binding.root
    }

    private fun prepareRecyclerView() {
        binding.mealRecyclerview.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = cdrFragmentAdapter
        }
    }

    override fun onMyItemClick(categoryPopularMeals: CategoryPopularMeals) {
//        Toast.makeText(requireContext(), "TBD", Toast.LENGTH_SHORT).show()
        val action = CategoryDRFragmentDirections.categoryDRFragmentToInfoOfMealFromCDRFragment(categoryPopularMeals)
        findNavController().navigate(action)
    }

    override fun onItemLongClick(categoryPopularMeals: CategoryPopularMeals) {
        Toast.makeText(requireContext(), "What You Wanna Eat This Type Of ${args.cdrItems.strCategory}?", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}