package com.example.varorussell.network.repositories

import com.example.varorussell.network.response.NowPlayingResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor() {
    private val favorites = mutableListOf<NowPlayingResponse.Result>()

    fun addToFavorites(movie: NowPlayingResponse.Result) {
        if(favorites.contains(movie)) {
            favorites.remove(movie)
        }
        else {
            favorites.add(movie)
        }
    }

    fun removeMovie(movie: NowPlayingResponse.Result) {
        if(favorites.contains(movie)) {
            favorites.remove(movie)
        }
    }

    fun getFavorites(): List<NowPlayingResponse.Result> = favorites

}