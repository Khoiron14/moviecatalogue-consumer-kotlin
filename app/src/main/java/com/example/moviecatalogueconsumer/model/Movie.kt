package com.example.moviecatalogueconsumer.model

/**
 * Created by khoiron14 on 9/7/2019.
 */
data class Movie(
    val id: Int? = null,
    val title: String? = null,
    val posterPath: String? = null
) {
    companion object {
        const val TABLE_NAME = "movieFavorite"
        const val COLUMN_MOVIE_ID = "movieId"
        const val COLUMN_TITLE = "title"
        const val COLUMN_POSTER_PATH = "posterPath"
    }
}