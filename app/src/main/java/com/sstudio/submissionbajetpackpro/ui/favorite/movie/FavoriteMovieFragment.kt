package com.sstudio.submissionbajetpackpro.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sstudio.submissionbajetpackpro.R
import com.sstudio.submissionbajetpackpro.data.source.local.entity.MovieFavorite
import com.sstudio.submissionbajetpackpro.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment(), FavoriteMovieAdapter.AdapterCallback {

    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter
    private val viewModel: FavoriteMovieViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        itemTouchHelper.attachToRecyclerView(rv_list_movie)
        if (activity != null) {

            favoriteMovieAdapter = FavoriteMovieAdapter(this)

            progress_bar.visibility = View.VISIBLE
            viewModel.listMovie?.observe(this, { listMovie ->
                favoriteMovieAdapter.submitList(listMovie)
                rv_list_movie.adapter = favoriteMovieAdapter
                //favoriteMovieAdapter.notifyDataSetChanged()
                progress_bar.visibility = View.GONE
            })

            with(rv_list_movie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
//                adapter = favoriteMovieAdapter
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val courseEntity = favoriteMovieAdapter.getSwipedData(swipedPosition)
                courseEntity?.let { viewModel.deleteFavorite(it.favoriteEntity.idMovieTv) }

                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    courseEntity?.let { viewModel.addFavorite(it.favoriteEntity.idMovieTv) }
                }
                snackbar.show()
            }
        }
    })

    override fun itemMovieOnclick(favorite: MovieFavorite) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE_TV, DetailActivity.IS_MOVIE)
        intent.putExtra(DetailActivity.EXTRA_DETAIL, favorite.movie.id)
        startActivity(intent)
    }
}