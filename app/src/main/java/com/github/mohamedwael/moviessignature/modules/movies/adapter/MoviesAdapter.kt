package com.github.mohamedwael.moviessignature.modules.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.mohamedwael.moviessignature.R
import com.github.mohamedwael.moviessignature.databinding.YearViewBinding
import com.github.mohamedwael.moviessignature.modules.movies.dto.MovieUIModel
import com.github.mohamedwael.moviessignature.modules.movies.widgets.MoviesView

private const val VIEW_TYPE_YEAR = 1

class MoviesAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val moviesList: MutableList<MovieUIModel> = mutableListOf()

    fun updateMoviesList(movies: List<MovieUIModel>) {
        moviesList.clear()
        moviesList.addAll(movies)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (moviesList[position].title == null && moviesList[position].details == null) {
            return VIEW_TYPE_YEAR
        }
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_YEAR) {
            return MoviesCategoryViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.year_view, null)
            )
        }

        return MoviesViewHolder(MoviesView(parent.context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        })
    }

    override fun getItemCount(): Int = moviesList.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MoviesViewHolder -> holder.bind(moviesList[position])
            is MoviesCategoryViewHolder -> holder.bind(moviesList[position].year)
        }
    }


    class MoviesViewHolder(moviesView: MoviesView) : RecyclerView.ViewHolder(moviesView) {
        fun bind(movieUIModel: MovieUIModel) {
            (itemView as MoviesView).setMovie(movieUIModel)
        }
    }

    class MoviesCategoryViewHolder(category: View) : RecyclerView.ViewHolder(category) {
        fun bind(year: Int?) {
            itemView.findViewById<TextView>(R.id.tvYear).text = year?.toString()
        }
    }
}