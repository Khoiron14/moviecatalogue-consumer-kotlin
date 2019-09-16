package com.example.moviecatalogueconsumer.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogueconsumer.getContentResolver
import com.example.moviecatalogueconsumer.getContentUri
import com.example.moviecatalogueconsumer.model.TvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvShowViewModel : ViewModel() {

    private val tvShowist = MutableLiveData<List<TvShow>>()

    fun setTvShowList() {
        CoroutineScope(Dispatchers.IO).launch {
            var favorites: List<TvShow> = emptyList()
            val tvShowCursor =
                getContentResolver().query(getContentUri(TvShow.TABLE_NAME), null, null, null, null)
            tvShowCursor?.let { mCursor ->
                favorites = generateSequence { if (mCursor.moveToNext()) mCursor else null }.map {
                    TvShow(
                        it.getInt(it.getColumnIndex(TvShow.COLUMN_TV_SHOW_ID)),
                        it.getString(it.getColumnIndex(TvShow.COLUMN_NAME)),
                        it.getString(it.getColumnIndex(TvShow.COLUMN_POSTER_PATH))
                    )
                }.toList()
            }
            tvShowist.postValue(favorites)
            tvShowCursor?.close()
        }
    }

    fun getTvShowList(): LiveData<List<TvShow>> = tvShowist
}