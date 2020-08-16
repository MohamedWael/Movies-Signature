package com.github.mohamedwael.moviessignature.modules.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.github.mohamedwael.moviessignature.databinding.MovieDetailsFragmentBinding
import com.github.mohamedwael.moviessignature.modules.moviedetails.viewmodel.MovieDetailsViewModel
import com.github.mohamedwael.moviessignature.modules.moviedetails.viewmodel.MovieDetailsViewModelFactory
import com.github.mohamedwael.moviessignature.modules.movies.widgets.MOVIE_ID_KEY

class MovieDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(
            this,
            MovieDetailsViewModelFactory
        ).get(MovieDetailsViewModel::class.java)
        viewModel.movieId.value = arguments?.getInt(MOVIE_ID_KEY)
        val binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

}