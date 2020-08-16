package com.github.mohamedwael.moviessignature.modules.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.mohamedwael.moviessignature.modules.movies.MoviesRepo

object MoviesViewModelFactory : ViewModelProvider.Factory {

    private lateinit var repo: MoviesRepo

    fun inject(repo: MoviesRepo) {
        this.repo = repo
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (MoviesViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(
                MoviesRepo::class.java
            ).newInstance(repo)
        } else {
            throw IllegalStateException("ViewModel must be MoviesViewModel")
        }
    }
}