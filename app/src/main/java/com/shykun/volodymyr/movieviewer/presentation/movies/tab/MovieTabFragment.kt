package com.shykun.volodymyr.movieviewer.presentation.movies.tab

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
import com.shykun.volodymyr.movieviewer.data.entity.MoviesType
import com.shykun.volodymyr.movieviewer.presentation.AppActivity
import kotlinx.android.synthetic.main.fragment_movies.*
import java.lang.Exception

const val POPULAR_MOVIES = 0
const val TOP_RATED_MOVIES = 1
const val UPCOMING_MOVIES = 2

class MovieTabFragment : MvpAppCompatFragment(), MovieTabView {

    @InjectPresenter
    lateinit var presenter: MovieTabPresenter
    lateinit var generalMovieTabAdapter: GeneralMovieTabAdapter

    @ProvidePresenter
    fun provideMoviesPresenter() = (activity as AppActivity).appComponent.getMoviesPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onViewLoaded()
        generalMovieTabAdapter = GeneralMovieTabAdapter(ArrayList(3))
        movieCategoryList.apply {
            layoutManager = LinearLayoutManager(this@MovieTabFragment.context, LinearLayoutManager.VERTICAL, false)
            adapter = generalMovieTabAdapter
        }
        setupSeeAllClick()
    }

    private fun setupSeeAllClick() {
        generalMovieTabAdapter.clickEvent.subscribe {
            val moviesType = when (it) {
                POPULAR_MOVIES -> MoviesType.POPULAR
                TOP_RATED_MOVIES -> MoviesType.TOP_RATED
                UPCOMING_MOVIES -> MoviesType.UPCOMING
                else -> throw Exception("Undefined movies type")
            }
            presenter.onViewAllButtonClicked(moviesType)
        }
    }

    override fun showPopularMovies(movies: ArrayList<Movie>) {
        generalMovieTabAdapter.addMovies(movies, POPULAR_MOVIES)
    }

    override fun showTopRatedMovies(movies: ArrayList<Movie>) {
        generalMovieTabAdapter.addMovies(movies, TOP_RATED_MOVIES)
    }

    override fun showUpcompingMovies(movies: ArrayList<Movie>) {
        generalMovieTabAdapter.addMovies(movies, UPCOMING_MOVIES)
    }

    override fun showError() {
        Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
    }
}
