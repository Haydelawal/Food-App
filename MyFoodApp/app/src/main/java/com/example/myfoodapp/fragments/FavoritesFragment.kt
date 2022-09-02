package com.example.myfoodapp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myfoodapp.R
import com.example.myfoodapp.data.model.randommeal.Meal
import com.example.myfoodapp.databinding.FragmentFavoritesBinding
import com.example.myfoodapp.fragments.home.adapters.favefragment.FavFragmentAdapter
import com.example.myfoodapp.viewmodel.MealViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), FavFragmentAdapter.ClickListener {

    private lateinit var _binding: FragmentFavoritesBinding
    private val binding get() = _binding!!

    private val mealViewModel: MealViewModel by viewModels()
    private val favFragmentAdapter: FavFragmentAdapter by lazy {
        FavFragmentAdapter(
            this
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.recyclerView.apply {
            adapter = favFragmentAdapter
            layoutManager =   GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        }

        mealViewModel.getAllMeals.observe(viewLifecycleOwner, Observer { meal ->
            favFragmentAdapter.setData(meal)
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_all, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.delete_all_items) {

            /// delete all meals
            deleteAllSavedMealData()

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }

    private fun deleteAllSavedMealData() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mealViewModel.deleteAllSavedMealData()
            Toast.makeText(requireContext(), "Deleted All Meals", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("No") { _, _ -> }
        builder.setMessage("Are you sure you want to delete ${mealViewModel.getAllMeals.value?.size} meals?")
        builder.create().show()
    }

    override fun onMyItemClick(meal: Meal) {
        val action = FavoritesFragmentDirections.favoritesFragmentToInfoFaveFragment(meal)
        findNavController().navigate(action)
//        Toast.makeText(requireContext(), "TBD", Toast.LENGTH_SHORT).show()
    }
}