package com.example.varorussell.db.di

import android.content.Context
import androidx.room.Room
import com.example.varorussell.db.AppDatabase
import com.example.varorussell.db.NowPlayingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "movie-database").build()

    @Provides
    fun provideCityDao(database: AppDatabase): NowPlayingDao = database.nowPlayingDao()
}