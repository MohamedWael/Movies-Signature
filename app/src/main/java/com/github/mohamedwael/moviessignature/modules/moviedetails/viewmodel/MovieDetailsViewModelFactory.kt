package com.github.mohamedwael.moviessignature.modules.moviedetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.mohamedwael.moviessignature.modules.movies.MoviesRepo

object MovieDetailsViewModelFactory : ViewModelProvider.Factory {

    private lateinit var repo: MoviesRepo

    fun inject(repo: MoviesRepo) {
        this.repo = repo
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (MovieDetailsViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(
                MoviesRepo::class.java
            ).newInstance(repo)
        } else {
            throw IllegalStateException("ViewModel must be MoviesViewModel")
        }
    }
}