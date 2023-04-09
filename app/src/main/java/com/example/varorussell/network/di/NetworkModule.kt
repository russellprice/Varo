package com.example.varorussell.network.di

import com.example.varorussell.network.api.MovieAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_NUMBER = 15L

    private const val BASE_URL = "https://api.themoviedb.org"
    const val BASE_IMAGE_URL ="https://image.tmdb.org/t/p/original"

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient().newBuilder()
            .readTimeout(TIMEOUT_NUMBER, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_NUMBER, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(gsonConverterFactory).build()

    @Provides
    fun provideMovieAPI(retrofit: Retrofit): MovieAPI = retrofit.create(MovieAPI::class.java)
}