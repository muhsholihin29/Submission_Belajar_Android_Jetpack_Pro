package com.sstudio.submissionbajetpackpro.ui.favorite.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sstudio.submissionbajetpackpro.R
import com.sstudio.submissionbajetpackpro.data.source.local.entity.TvFavorite
import com.sstudio.submissionbajetpackpro.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvShowFragment : Fragment(), FavoriteTvShowAdapter.AdapterCallback {

    private lateinit var favoriteTvShowAdapter: FavoriteTvShowAdapter
    private val viewModel: FavoriteTvShowViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            favoriteTvShowAdapter = FavoriteTvShowAdapter(this)

            progress_bar.visibility = View.VISIBLE
            viewModel.listTv?.observe(this, { listTv ->
                favoriteTvShowAdapter.submitList(listTv)
//                favoriteTvShowAdapter.notifyDataSetChanged()
                rv_list_tv_show.adapter = favoriteTvShowAdapter
                progress_bar.visibility = View.GONE
            })

            with(rv_list_tv_show) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
//                adapter = favoriteTvShowAdapter
            }
        }
    }

    override fun itemMovieOnclick(favorite: TvFavorite) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE_TV, DetailActivity.IS_MOVIE)
        intent.putExtra(DetailActivity.EXTRA_DETAIL, favorite.tv.id)
        startActivity(intent)
    }
}