package com.example.myfoodapp.fragments

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
import com.example.myfoodapp.data.model.doublerecycler.Category
import com.example.myfoodapp.databinding.FragmentCategoriesBinding
import com.example.myfoodapp.databinding.FragmentHomeBinding
import com.example.myfoodapp.fragments.home.HomeFragmentDirections
import com.example.myfoodapp.fragments.home.adapters.doublehomerecycler.ClickListenerDR
import com.example.myfoodapp.fragments.home.adapters.doublehomerecycler.DoubleRecyclerAdapter
import com.example.myfoodapp.fragments.home.adapters.doublehomerecycler.OnLongItemClickDR
import com.example.myfoodapp.fragments.home.adapters.popularhome.MostPopularRecyclerAdapter
import com.example.myfoodapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment(), ClickListenerDR, OnLongItemClickDR {
    private lateinit var _binding: FragmentCategoriesBinding
    private val binding get() = _binding!!

    private val myViewModel: HomeViewModel by viewModels()

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
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)



        // double recycler view
        myViewModel.observeDRGetCategoriesLiveData()
        observeDRCategoriesLivaData()
        recyclerViewDR()
        // double recycler///


        return binding.root
    }

    private fun recyclerViewDR() {
        binding.recyclerViewCategoriesDr.apply {
            layoutManager = GridLayoutManager(
                requireContext(), 2, GridLayoutManager.VERTICAL, false
            )
            adapter = myDRCategoryAdapter
        }
    }



    private fun observeDRCategoriesLivaData() {
        myViewModel.observeDRGetCategoriesLiveData()
            .observe(viewLifecycleOwner, Observer { categories ->
                myDRCategoryAdapter.setCategoryDRList(categories)
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }

    override fun onMyItemClick(category: Category) {
        val action = CategoriesFragmentDirections.actionCategoriesFragmentToCategoryDRFragment(category)
        findNavController().navigate(action)
            }

    override fun onItemLongClick(category: Category) {
        Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
    }
}