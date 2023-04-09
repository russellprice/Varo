package com.example.varorussell.movieList

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.varorussell.architecture.BaseViewModel
import com.example.varorussell.network.NetworkResult
import com.example.varorussell.network.repositories.FavoriteRepository
import com.example.varorussell.network.repositories.MovieRepository
import com.example.varorussell.network.response.NowPlayingResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieRepository: MovieRepository, private val favoriteRepository: FavoriteRepository) : BaseViewModel() {

    @VisibleForTesting
    lateinit var data: NowPlayingResponse

    val movies: StateFlow<List<NowPlayingResponse.Result>> = MutableStateFlow(emptyList())

    init {
        fetchMovies()
    }


    @VisibleForTesting
    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = movieRepository.fetchNowPlayingMovies()) {
                is NetworkResult.Success<*> -> {
                    data = (result.data as NowPlayingResponse)

                    movies.tryToEmit(data.results)
                    Log.d("test", result.toString())
                }
                else -> {
                    Log.d("test", "error")
                }
            }
        }
    }

    fun addToFavorites(movie: NowPlayingResponse.Result) {
        favoriteRepository.addToFavorites(movie)
    }
}