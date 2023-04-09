package com.example.varorussell

import app.cash.turbine.test
import com.example.varorussell.movieList.MovieListViewModel
import com.example.varorussell.network.NetworkResult
import com.example.varorussell.network.repositories.FavoriteRepository
import com.example.varorussell.network.repositories.MovieRepository
import com.example.varorussell.network.response.NowPlayingResponse
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class MovieListViewModelTest {

    private val movieRepository = mockk<MovieRepository>()
    private val favoriteRepository = spyk<FavoriteRepository>()


    @Test
    fun `viewModel init`() = runTest {
        coEvery {  movieRepository.fetchNowPlayingMovies() } returns  VALID_MOVIE_LIST_RESULT
        val viewModel = MovieListViewModel(movieRepository, favoriteRepository)
        viewModel.movies.test {
            Assert.assertEquals(VALID_MOVIE_LIST_RESULT.data, viewModel.data)
            Assert.assertEquals(VALID_MOVIE_LIST_RESULT.data.results, expectMostRecentItem())
        }
    }

    @Test
    fun `viewModel addToFavorites`() {
        val viewModel = MovieListViewModel(movieRepository, favoriteRepository)
        Assert.assertEquals(0, favoriteRepository.getFavorites().size)
        viewModel.apply {
            addToFavorites(FavoriteViewModelTest.VALID_FAVORITES[0])
            Assert.assertEquals(1, favoriteRepository.getFavorites().size)
        }
    }


    companion object {
        val VALID_MOVIE_LIST_RESULT =
            NetworkResult.Success(
                NowPlayingResponse(
                    dates = NowPlayingResponse.Dates(maximum = "2023-04-13", minimum = "2023-02-24"),
                    page = 1,
                    results = FavoriteViewModelTest.VALID_FAVORITES,
                    total_pages = 1,
                    total_results = 890
                )
            )
    }


}