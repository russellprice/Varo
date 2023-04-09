package com.example.varorussell.network.repositories

import com.example.varorussell.network.api.MovieAPI
import com.example.varorussell.network.runNetworkCatching
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val api: MovieAPI
) {
    suspend fun fetchNowPlayingMovies() = withContext(Dispatchers.IO) {
        runNetworkCatching {
            api.getNowPlayingMovies()
        }
    }
}