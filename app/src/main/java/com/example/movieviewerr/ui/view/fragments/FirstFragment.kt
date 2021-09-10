package com.example.movieviewerr.ui.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.movieviewerr.R
import com.example.movieviewerr.adapter.MoviesAdapter
import com.example.movieviewerr.databinding.FragmentFirstBinding
import com.example.movieviewerr.repository.Resource
import com.example.movieviewerr.mvvm.extensions.viewLifecycleLazy
import com.example.movieviewerr.ui.view.MainActivity
import com.example.movieviewerr.ui.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : BaseFragment(R.layout.fragment_first) {

    private val binding by viewLifecycleLazy { FragmentFirstBinding.bind(requireView()) }
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAPI()
    }







    private fun setupAPI(){


        mainViewModel.moviesData.observe(viewLifecycleOwner){ moviesData ->

            when(moviesData.status){

                Resource.Status.SUCCESS -> {
                    binding.noInternetTxt.isVisible = false
                    binding.mainRecycler.isVisible = true
                    binding.mainRecycler.adapter = MoviesAdapter(moviesData.data!!)
                    hideLoading()
                }

                Resource.Status.ERROR -> {
                    binding.mainRecycler.isVisible = false
                    binding.noInternetTxt.isVisible = true

                    hideLoading()
                    Log.e("MainFragment", moviesData.message.toString())
                }

                Resource.Status.LOADING -> {
                    showLoading(false, requireActivity() as MainActivity)
                }
            }
        }

        mainViewModel.listenForMovies()
    }

}