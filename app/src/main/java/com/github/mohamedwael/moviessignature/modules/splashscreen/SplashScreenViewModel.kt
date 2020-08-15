package com.github.mohamedwael.moviessignature.modules.splashscreen

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.blogspot.mowael.utilslibrary.utils.SingleLiveDataEvent

const val SPLASH_SCREEN_DURATION = 3000L

class SplashScreenViewModel : ViewModel() {
    private val handler = Handler(Looper.getMainLooper())
    private val _home = MutableLiveData<SingleLiveDataEvent<Boolean>>()
    val home: LiveData<SingleLiveDataEvent<Boolean>> = _home

    init {
        handler.postDelayed(::fireNav, SPLASH_SCREEN_DURATION)
    }

    private fun fireNav() {
        _home.value = SingleLiveDataEvent(true)
    }

    override fun onCleared() {
        handler.removeCallbacks(::fireNav)
        super.onCleared()
    }
}