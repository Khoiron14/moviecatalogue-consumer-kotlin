package com.example.moviecatalogueconsumer.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogueconsumer.getContentResolver
import com.example.moviecatalogueconsumer.getContentUri
import com.example.moviecatalogueconsumer.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val movieList = MutableLiveData<List<Movie>>()

    fun setMovieList() {
        CoroutineScope(Dispatchers.IO).launch {
            var favorites: List<Movie> = emptyList()
            val movieCursor =
                getContentResolver().query(getContentUri(Movie.TABLE_NAME), null, null, null, null)
            movieCursor?.let { mCursor ->
                favorites = generateSequence { if (mCursor.moveToNext()) mCursor else null }.map {
                    Movie(
                        it.getInt(it.getColumnIndex(Movie.COLUMN_MOVIE_ID)),
                        it.getString(it.getColumnIndex(Movie.COLUMN_TITLE)),
                        it.getString(it.getColumnIndex(Movie.COLUMN_POSTER_PATH))
                    )
                }.toList()
            }
            movieList.postValue(favorites)
            movieCursor?.close()
        }
    }

    fun getMovieList(): LiveData<List<Movie>> = movieList
}