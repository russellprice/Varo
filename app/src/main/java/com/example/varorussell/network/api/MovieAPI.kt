package com.example.varorussell.network.api

import com.example.varorussell.BuildConfig
import com.example.varorussell.network.response.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {


    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") key: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1
    ): NowPlayingResponse
}