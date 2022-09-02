package com.example.myfoodapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myfoodapp.databinding.FragmentInfoFaveBinding
import com.example.myfoodapp.viewmodel.MealViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoFaveFragment : Fragment() {

    private lateinit var _binding: FragmentInfoFaveBinding
    private val binding get() = _binding!!

    private val mealViewModel: MealViewModel by viewModels()

    private val args: InfoFaveFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInfoFaveBinding.inflate(inflater, container, false)


        Glide.with(requireContext())
            .load(args.infoFaveMeal.strMealThumb)
            .into(binding.imgMealDetail)

        binding.tvCategoryInfo.text = "Category: ${args.infoFaveMeal.strCategory}"

        binding.tvAreaInfo.text = "Area: ${args.infoFaveMeal.strArea}"

        binding.collapsingToolbar.apply {
            title = args.infoFaveMeal.strMeal
            setCollapsedTitleTextColor(resources.getColor(R.color.white))
            setExpandedTitleColor(resources.getColor(R.color.white))
        }

        binding.tvContent.text = args.infoFaveMeal.strInstructions

        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(args.infoFaveMeal.strYoutube))
            startActivity(intent)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.delete_item) {
            /// delete all meals
            deleteMeal()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteMeal() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mealViewModel.deleteMeal(args.infoFaveMeal)
            val action = InfoFaveFragmentDirections.infoFaveFragmentToFavoritesFragment()
            findNavController().navigate(action)
            Toast.makeText(
                requireContext(),
                "Deleted ${args.infoFaveMeal.strMeal} ${args.infoFaveMeal.idMeal} Meal",
                Toast.LENGTH_SHORT
            ).show()
        }

        builder.setNegativeButton("No") { _, _ -> }
        builder.setMessage("Are you sure you want to delete ${args.infoFaveMeal.strMeal} ${args.infoFaveMeal.idMeal} Meal?")
        builder.create().show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding.root
    }
}