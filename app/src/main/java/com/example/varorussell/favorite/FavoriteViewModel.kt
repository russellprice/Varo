package com.example.varorussell.favorite

import com.example.varorussell.architecture.BaseViewModel
import com.example.varorussell.network.repositories.FavoriteRepository
import com.example.varorussell.network.response.NowPlayingResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteRepository: FavoriteRepository) : BaseViewModel() {

    val favorites: StateFlow<List<NowPlayingResponse.Result>> = MutableStateFlow(favoriteRepository.getFavorites())

    fun removeFavorite(movie: NowPlayingResponse.Result) {
        favoriteRepository.removeMovie(movie)
        favorites.tryToEmit(favoriteRepository.getFavorites())
    }


}