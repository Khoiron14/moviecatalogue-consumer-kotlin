package com.example.moviecatalogueconsumer

import android.content.ContentResolver
import android.net.Uri
import android.view.View
import android.widget.ProgressBar

/**
 * Created by khoiron14 on 9/7/2019.
 */
const val AUTHORITY = "com.khoiron14.moviecatalogue"

fun getContentResolver(): ContentResolver {
    return MovieCatalogueConsumer.applicationContext().contentResolver
}

fun getContentUri(table: String?): Uri {
    return Uri.Builder().scheme("content")
        .authority(AUTHORITY)
        .appendPath(table)
        .build()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun showLoading(state: Boolean, progressBar: ProgressBar) {
    if (state) {
        progressBar.visible()
    } else {
        progressBar.gone()
    }
}