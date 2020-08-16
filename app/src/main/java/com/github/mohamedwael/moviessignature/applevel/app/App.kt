package com.github.mohamedwael.moviessignature.applevel.app

import android.app.Application
import com.github.mohamedwael.moviessignature.applevel.utils.RawJsonFileParser
import com.github.mohamedwael.moviessignature.modules.movies.MoviesRepo
import com.github.mohamedwael.moviessignature.modules.movies.viewmodel.MoviesViewModelFactory

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        MoviesViewModelFactory.inject(MoviesRepo(RawJsonFileParser(applicationContext)))
    }
}