package com.github.mohamedwael.moviessignature.modules.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.github.mohamedwael.moviessignature.R
import com.github.mohamedwael.moviessignature.databinding.SplashScreenFragmentBinding

class SplashScreenFragment : Fragment() {

    companion object {
        fun newInstance() =
            SplashScreenFragment()
    }

    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SplashScreenViewModel::class.java)
        val binding = SplashScreenFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.home.observe(viewLifecycleOwner, Observer {
            if (it.getContentIfNotHandled() == true){
                Navigation.findNavController(view).navigate(R.id.action_splashScreenFragment_to_moviesFragment)
            }
        })
    }


}