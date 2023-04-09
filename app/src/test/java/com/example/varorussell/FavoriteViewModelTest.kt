package com.example.varorussell

import app.cash.turbine.test
import com.example.varorussell.favorite.FavoriteViewModel
import com.example.varorussell.network.repositories.FavoriteRepository
import io.mockk.every
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import com.example.varorussell.network.response.NowPlayingResponse.Result as Movie


class FavoriteViewModelTest {

    val repository = spyk<FavoriteRepository>()

    @Test
    fun `FavoriteViewModel init`() {
        every { repository.getFavorites() } returns VALID_FAVORITES
        FavoriteViewModel(repository).apply {
            Assert.assertTrue(this.favorites.value.isNotEmpty())
            Assert.assertEquals(VALID_FAVORITES, this.favorites.value)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `FavoriteViewModel removeFavorite`() = runTest {
        repository.addToFavorites(VALID_FAVORITES[0])
        repository.addToFavorites(VALID_FAVORITES[1])
        val viewModel = FavoriteViewModel(repository)
        viewModel.favorites.test {
            viewModel.removeFavorite(VALID_FAVORITES[0])
            Assert.assertEquals(VALID_FAVORITES[1], expectMostRecentItem()[0])
        }
    }


    companion object {
        val VALID_FAVORITES = listOf(
            Movie(
                adult = false,
                backdrop_path = "/5i6SjyDbDWqyun8klUuCxrlFbyw.jpg",
                genre_ids = listOf(1, 2),
                id = 1,
                original_language = "en",
                original_title = "movie1",
                overview = "a movie that has people in it",
                popularity = 123.00,
                poster_path = "/494jwlisKKKJ.jpg",
                release_date = "today",
                title = "movie1",
                video = true,
                vote_average = 1.0,
                vote_count = 123000
            ),
            Movie(
                adult = false,
                backdrop_path = "/5i6SjyDbyun8klUuCxrlFbyw.jpg",
                genre_ids = listOf(3, 4),
                id = 2,
                original_language = "en",
                original_title = "movie2",
                overview = "a movie that has animals in it",
                popularity = 123.00,
                poster_path = "/494jwlisJ.jpg",
                release_date = "today",
                title = "movie2",
                video = true,
                vote_average = 2.0,
                vote_count = 120000
            )

        )
    }
}