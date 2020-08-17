package com.github.mohamedwael.moviessignature.modules.moviedetails.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.blogspot.mowael.retrofitcore.services.ErrorHandler
import com.blogspot.mowael.utilslibrary.utils.SingleLiveDataEvent
import com.github.mohamedwael.moviessignature.applevel.utils.toUIModel
import com.github.mohamedwael.moviessignature.modules.moviedetails.dto.MovieDetails
import com.github.mohamedwael.moviessignature.modules.moviedetails.repo.ImagesRepo
import com.github.mohamedwael.moviessignature.modules.moviedetails.repo.dto.PhotoItem
import com.github.mohamedwael.moviessignature.modules.movies.MoviesRepo

class MovieDetailsViewModel(repo: MoviesRepo, imagesRepo: ImagesRepo) : ViewModel() {
    val movieId = MutableLiveData<Int>()
    val photos = MutableLiveData<List<PhotoItem>>()
    val secondPhotoList = MutableLiveData<List<PhotoItem>>()
    val errorHandler = MutableLiveData<SingleLiveDataEvent<ErrorHandler>>()
    val progressVisibility = MutableLiveData<Boolean>(true)
    val movie = MediatorLiveData<MovieDetails>().apply {
        addSource(photos) {
            progressVisibility.value = false
            Log.d("MovieDetailsViewModel", "progress false")
        }

        addSource(movieId) { index ->
            val item = repo.getMovieById(index).value
            item?.also {
                imagesRepo.getImageList(it.title, {
                    photos.value = it.subList(0, it.size/2)
                    secondPhotoList.value = it.subList(it.size/2+1, it.size)

                }, {
                    progressVisibility.value = false
                    errorHandler.value = SingleLiveDataEvent(it)
                })
                value = MovieDetails(item.toUIModel(index), item.cast)
            }
        }
    }

}