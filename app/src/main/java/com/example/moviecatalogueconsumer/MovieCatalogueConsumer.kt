package com.example.moviecatalogueconsumer

import android.app.Application
import android.content.Context

/**
 * Created by khoiron14 on 9/7/2019.
 */
class MovieCatalogueConsumer : Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: MovieCatalogueConsumer

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}