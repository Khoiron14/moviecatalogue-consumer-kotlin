package com.example.moviecatalogueconsumer.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviecatalogueconsumer.R
import com.example.moviecatalogueconsumer.gone
import com.example.moviecatalogueconsumer.model.Movie
import com.example.moviecatalogueconsumer.showLoading
import com.example.moviecatalogueconsumer.visible
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var adapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    private val getMovieList =
        Observer<List<Movie>> { movieList ->
            if (movieList.isNotEmpty()) {
                adapter.setData(movieList)
                rv_list_movie.visible()
                no_favorite.gone()
            } else {
                rv_list_movie.gone()
                no_favorite.visible()
            }
            showLoading(false, progress_bar)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieAdapter()
        rv_list_movie.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        fetchData()
    }

    override fun onStop() {
        super.onStop()
        fetchData()
    }

    private fun fetchData() {
        movieViewModel.getMovieList().observe(this, getMovieList)
        movieViewModel.setMovieList()
        showLoading(true, progress_bar)
    }
}