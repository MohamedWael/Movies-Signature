package com.github.mohamedwael.moviessignature.applevel.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.github.mohamedwael.moviessignature.modules.moviedetails.repo.dto.PhotoItem
import com.github.mohamedwael.moviessignature.modules.movies.dto.MovieUIModel
import com.github.mohamedwael.moviessignature.modules.movies.dto.MoviesItem

fun hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = activity.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun MoviesItem.toUIModel() = MovieUIModel(
    title?.trim(),
    rating?.toString() + " . " +
            genres
                .toString()
                .replace(",", "/")
                .replace("[", "")
                .replace("]", ""),
    year
)

fun createImageURL(photoItem: PhotoItem) =
    "http://farm${photoItem.farm}.static.flickr.com/${photoItem.server}/${photoItem.id}_${photoItem.secret}.jpg"

