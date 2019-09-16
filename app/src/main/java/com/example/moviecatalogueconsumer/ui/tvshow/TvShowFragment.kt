package com.example.moviecatalogueconsumer.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviecatalogueconsumer.R
import com.example.moviecatalogueconsumer.gone
import com.example.moviecatalogueconsumer.model.TvShow
import com.example.moviecatalogueconsumer.showLoading
import com.example.moviecatalogueconsumer.visible
import kotlinx.android.synthetic.main.fragment_movie.no_favorite
import kotlinx.android.synthetic.main.fragment_movie.progress_bar
import kotlinx.android.synthetic.main.fragment_tvshow.*

class TvShowFragment : Fragment() {

    private lateinit var adapter: TvShowAdapter
    private lateinit var tvShowViewModel: TvShowViewModel

    private val getTvShowList =
        Observer<List<TvShow>> { tvShowList ->
            if (tvShowList.isNotEmpty()) {
                adapter.setData(tvShowList)
                rv_list_tvshow.visible()
                no_favorite.gone()
            } else {
                rv_list_tvshow.gone()
                no_favorite.visible()
            }
            showLoading(false, progress_bar)
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel::class.java)
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TvShowAdapter()
        rv_list_tvshow.adapter = adapter
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
        tvShowViewModel.getTvShowList().observe(this, getTvShowList)
        tvShowViewModel.setTvShowList()
        showLoading(true, progress_bar)
    }
}