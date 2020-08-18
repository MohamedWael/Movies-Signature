package com.github.mohamedwael.moviessignature.modules.moviedetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.mohamedwael.moviessignature.modules.moviedetails.repo.ImagesRepo
import com.github.mohamedwael.moviessignature.modules.movies.MoviesRepo

object MovieDetailsViewModelFactory : ViewModelProvider.Factory {

    private lateinit var repo: MoviesRepo
    private lateinit var imagesRepo: ImagesRepo

    fun inject(
        repo: MoviesRepo,
        imagesRepo: ImagesRepo
    ) {
        this.repo = repo
        this.imagesRepo = imagesRepo
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (MovieDetailsViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(
                MoviesRepo::class.java,
                ImagesRepo::class.java
            ).newInstance(repo, imagesRepo)
        } else {
            throw IllegalStateException("ViewModel must be MovieDetailsViewModel")
        }
    }
}