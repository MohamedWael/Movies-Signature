package com.github.mohamedwael.moviessignature.modules.movies.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.github.mohamedwael.moviessignature.modules.movies.MoviesRepo
import com.github.mohamedwael.moviessignature.modules.movies.dto.MovieUIModel
import com.github.mohamedwael.moviessignature.modules.movies.dto.MoviesItem
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import java.lang.reflect.Field

class MoviesViewModelTest {

    @Rule
    @JvmField
    val taskRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MoviesViewModel
    private val moviesLiveData = Mockito.spy(MutableLiveData<List<MoviesItem>>(getMoviesItems()))

    @Before
    fun setUp() {
        val moviesRepo = Mockito.mock(MoviesRepo::class.java)
        Mockito.`when`(moviesRepo.getMovies()).thenReturn(moviesLiveData)
        viewModel = Mockito.spy(MoviesViewModel(moviesRepo))
    }

    @Test
    fun verify_search_returns_found_item_and_year_category_item() {
        viewModel.search("bat")
        val searchableMoviesField = getViewModelField("searchableMovies")
        val searchableMovies =
            searchableMoviesField.get(viewModel) as MutableLiveData<List<MovieUIModel>>
        assertTrue(searchableMovies.value?.getOrNull(1)?.title == "batman")
        assertTrue(searchableMovies.value?.size == 2)
    }

    @Test
    fun verify_search_returns_zero_items_if_no_item_found() {
        viewModel.search("cars")
        val searchableMoviesField = getViewModelField("searchableMovies")
        val searchableMovies =
            searchableMoviesField.get(viewModel) as MutableLiveData<List<MovieUIModel>>
        assertTrue(searchableMovies.value?.size == 0)
    }

    @Test
    fun verify_MoviesItems_converted_to_MovieUIModel_list_with_year_categories_when_calling_createMovieUIModel() {
        val movieUIModelList = viewModel.createMovieUIModel(getMoviesItems())
        assertTrue(movieUIModelList.size == 12)
        assertTrue(movieUIModelList.first().title == null && movieUIModelList.first().year == 2009)
        assertTrue(movieUIModelList[1].title == getMoviesItems().first().title)
        assertTrue(movieUIModelList.last().title == getMoviesItems().last().title)
    }

    @Test
    fun verify_MoviesItems_reduced_to_5_only_when_calling_reduceToTopRated5_plus_category_item() {
        val movieUIModelList = viewModel.reduceToTopRated5(getTopRatedMovies())
        val topRatedMovies = movieUIModelList.filter { it.year == 2009 }
        assertTrue(topRatedMovies.size == 6)
    }

    @Test
    fun verify_top_MoviesItems_returned_if_less_than_5_reduceToTopRated5() {
        val movieUIModelList = viewModel.reduceToTopRated5(getMoviesItems())
        val topRatedMovies = movieUIModelList.filter { it.year == 2009 }
        assertTrue(topRatedMovies.size == 2)
    }


    @Test
    fun verify_onQueryTextChange_calls_search_method() {
        val query = "batman"
        viewModel.queryTextListener.onQueryTextChange(query)
        val searchableMoviesField = getViewModelField("searchableMovies")
        val searchableMovies =
            searchableMoviesField.get(viewModel) as MutableLiveData<List<MovieUIModel>>
        assertTrue(searchableMovies.value?.getOrNull(1)?.title == "batman")
        assertTrue(searchableMovies.value?.size == 2)
    }


    @Test
    fun verify_onQueryTextSubmit_triggers_hideKeyboard_event() {
        assertTrue(viewModel.hideKeyboard.value?.getContentIfNotHandled() == null)
        viewModel.queryTextListener.onQueryTextSubmit("query")
        assertTrue(viewModel.hideKeyboard.value?.getContentIfNotHandled() == true)
    }


    private fun getViewModelField(fieldName: String): Field {
        val field = MoviesViewModel::class.java.getDeclaredField(fieldName)
        field.isAccessible = true
        return field
    }

    private fun getMoviesItems(): List<MoviesItem> = listOf(
        MoviesItem("batman", 2009, listOf("tom", "jerry"), listOf("action", "comedy"), 5),
        MoviesItem("superman", 2010, listOf("tom", "jerry"), listOf("comedy"), 4),
        MoviesItem("spider man", 2011, listOf("tom", "jerry"), listOf("drama"), 3),
        MoviesItem("Big hero 6", 2012, listOf("tom", "jerry"), listOf("comedy"), 2),
        MoviesItem("ant", 2013, listOf("tom", "jerry"), listOf("comedy"), 1),
        MoviesItem("bee", 2014, listOf("tom", "jerry"), listOf("comedy"), 5)
    )

    private fun getTopRatedMovies(): List<MoviesItem> = listOf(
        MoviesItem("batman1", 2009, listOf("tom", "jerry"), listOf("action", "comedy"), 5),
        MoviesItem("batman2", 2009, listOf("tom", "jerry"), listOf("action", "comedy"), 4),
        MoviesItem("batman3", 2009, listOf("tom", "jerry"), listOf("action", "comedy"), 6),
        MoviesItem("batman4", 2009, listOf("tom", "jerry"), listOf("action", "comedy"), 7),
        MoviesItem("batman5", 2009, listOf("tom", "jerry"), listOf("action", "comedy"), 8),
        MoviesItem("batman6", 2009, listOf("tom", "jerry"), listOf("action", "comedy"), 6),
        MoviesItem("batman7", 2009, listOf("tom", "jerry"), listOf("action", "comedy"), 7),
        MoviesItem("batman8", 2009, listOf("tom", "jerry"), listOf("action", "comedy"), 8),
        MoviesItem("superman", 2010, listOf("tom", "jerry"), listOf("comedy"), 5),
        MoviesItem("spider man", 2011, listOf("tom", "jerry"), listOf("drama"), 5),
        MoviesItem("Big hero 6", 2012, listOf("tom", "jerry"), listOf("comedy"), 5),
        MoviesItem("ant", 2013, listOf("tom", "jerry"), listOf("comedy"), 6),
        MoviesItem("bee", 2014, listOf("tom", "jerry"), listOf("comedy"), 5)
    )

    private fun <T> anyObject(): T = Mockito.any<T>()

}