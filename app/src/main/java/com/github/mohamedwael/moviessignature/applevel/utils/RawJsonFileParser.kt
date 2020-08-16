package com.github.mohamedwael.moviessignature.applevel.utils

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson
import java.io.InputStreamReader

class RawJsonFileParser<T>(private val context: Context) : (Int, Class<T>) -> T? {

    override fun invoke(@RawRes rawFileId: Int, outputClass: Class<T>): T? =
        InputStreamReader(context.resources.openRawResource(rawFileId), "UTF-8").let {
            Gson().fromJson(it, outputClass)
        }
}