package com.example.moviecatalogueconsumer.model

/**
 * Created by khoiron14 on 9/8/2019.
 */
data class TvShow(
    val id: Int? = null,
    val name: String? = null,
    val posterPath: String? = null
) {
    companion object {
        const val TABLE_NAME = "tvShowFavorite"
        const val COLUMN_TV_SHOW_ID = "tvShowId"
        const val COLUMN_NAME = "name"
        const val COLUMN_POSTER_PATH = "posterPath"
    }
}