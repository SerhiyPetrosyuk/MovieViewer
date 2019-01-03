package com.shykun.volodymyr.movieviewer.presentation.movies

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter

import com.shykun.volodymyr.movieviewer.R
import com.shykun.volodymyr.movieviewer.data.entity.Movie
import com.shykun.volodymyr.movieviewer.presentation.AppActivity
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

const val POPULAR_MOVIES = 0
const val TOP_RATED_MOVIES = 1
const val UPCOMING_MOVIES = 2

class MoviesFragment : MvpAppCompatFragment(), MoviesView {

    @Inject
    @InjectPresenter
    lateinit var presenter: MoviesPresenter
    lateinit var generalMoviesAdapter: GeneralMoviesAdapter

    @ProvidePresenter
    fun provideMoviesPresenter() = (activity as AppActivity).appComponent.getMoviesPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
//        (activity as AppActivity).appComponent.inject(this)
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewLoaded()
        generalMoviesAdapter = GeneralMoviesAdapter(ArrayList(3))
        movieList.apply {
            layoutManager = LinearLayoutManager(this@MoviesFragment.context, LinearLayoutManager.VERTICAL, false)
            adapter = generalMoviesAdapter
        }
    }

    override fun showPopularMovies(movies: ArrayList<Movie>) {
        generalMoviesAdapter.addMovies(movies, POPULAR_MOVIES)
    }

    override fun showTopRatedMovies(movies: ArrayList<Movie>) {
        generalMoviesAdapter.addMovies(movies, TOP_RATED_MOVIES)
    }

    override fun showUpcompingMovies(movies: ArrayList<Movie>) {
        generalMoviesAdapter.addMovies(movies, UPCOMING_MOVIES)
    }

    override fun showError() {
        Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
    }
}
