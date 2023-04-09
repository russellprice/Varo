package com.example.varorussell

import android.app.Application
import com.squareup.picasso.Picasso
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VaroApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Picasso.Builder(this).build()
    }
}