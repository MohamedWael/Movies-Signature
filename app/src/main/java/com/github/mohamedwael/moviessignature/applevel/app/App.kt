package com.github.mohamedwael.moviessignature.applevel.app

import android.app.Application

import com.blogspot.mowael.retrofitcore.RetrofitBase
import com.github.mohamedwael.moviessignature.BuildConfig
import com.github.mohamedwael.moviessignature.applevel.utils.RawJsonFileParser
import com.github.mohamedwael.moviessignature.modules.moviedetails.repo.FLICKER_BASE_URL
import com.github.mohamedwael.moviessignature.modules.moviedetails.repo.ImagesRepo
import com.github.mohamedwael.moviessignature.modules.moviedetails.viewmodel.MovieDetailsViewModelFactory
import com.github.mohamedwael.moviessignature.modules.movies.MoviesRepo
import com.github.mohamedwael.moviessignature.modules.movies.viewmodel.MoviesViewModelFactory

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val repo = MoviesRepo(RawJsonFileParser(applicationContext))
        MoviesViewModelFactory.inject(repo)
        MovieDetailsViewModelFactory.inject(repo, ImagesRepo())
        RetrofitBase.initialize(FLICKER_BASE_URL, BuildConfig.DEBUG)
    }
}