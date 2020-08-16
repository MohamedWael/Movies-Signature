package com.github.mohamedwael.moviessignature.modules.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mohamedwael.moviessignature.applevel.utils.hideKeyboard
import com.github.mohamedwael.moviessignature.databinding.MoviesFragmentBinding
import com.github.mohamedwael.moviessignature.modules.movies.viewmodel.MoviesViewModel
import com.github.mohamedwael.moviessignature.modules.movies.viewmodel.MoviesViewModelFactory

class MoviesFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, MoviesViewModelFactory).get(MoviesViewModel::class.java)
        val binding = MoviesFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.hideKeyboard.observe(viewLifecycleOwner, Observer {
            val hideKeyboardValue = it.getContentIfNotHandled()
            if (hideKeyboardValue == true) {
                activity?.also { activity ->
                    hideKeyboard(activity)
                }
            }
        })
    }

}