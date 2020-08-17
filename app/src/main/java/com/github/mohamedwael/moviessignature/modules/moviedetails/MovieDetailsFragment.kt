package com.github.mohamedwael.moviessignature.modules.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mohamedwael.moviessignature.R
import com.github.mohamedwael.moviessignature.databinding.MovieDetailsFragmentBinding
import com.github.mohamedwael.moviessignature.modules.moviedetails.adapter.ImagePosterAdapter
import com.github.mohamedwael.moviessignature.modules.moviedetails.viewmodel.MovieDetailsViewModel
import com.github.mohamedwael.moviessignature.modules.moviedetails.viewmodel.MovieDetailsViewModelFactory
import com.github.mohamedwael.moviessignature.modules.movies.widgets.MOVIE_ID_KEY

class MovieDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    private lateinit var viewModel: MovieDetailsViewModel
    private val dialog by lazy {
        AlertDialog.Builder(requireContext()).setView(R.layout.loading_layout)
            .setCancelable(false).create()
    }

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
        binding.rvFirstPosterList.layoutManager = LinearLayoutManager(context)
        binding.rvFirstPosterList.adapter = ImagePosterAdapter()
        binding.rvSecondPoster.layoutManager = LinearLayoutManager(context)
        binding.rvSecondPoster.adapter = ImagePosterAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.progressVisibility.observe(viewLifecycleOwner, Observer { showProgress ->
            if (showProgress) {
                dialog.show()
            } else {
                dialog.dismiss()
            }
        })
        viewModel.errorHandler.observe(viewLifecycleOwner, Observer { errorEvent ->
            val error = errorEvent.getContentIfNotHandled()
            if (error?.errorMsgStringRes != 0 && error?.errorMsgStringRes != null) {
                Toast.makeText(context, error.errorMsgStringRes, Toast.LENGTH_LONG).show()
            } else if (!error?.errorMsgString.isNullOrEmpty()) {
                Toast.makeText(context, error?.errorMsgString, Toast.LENGTH_LONG).show()
            }
        })
    }

}