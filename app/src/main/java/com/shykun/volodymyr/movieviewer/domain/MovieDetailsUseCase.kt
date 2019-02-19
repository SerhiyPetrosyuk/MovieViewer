package com.shykun.volodymyr.movieviewer.domain

import com.shykun.volodymyr.movieviewer.data.network.ApiClient
import com.shykun.volodymyr.movieviewer.data.network.body.RateBody
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(private val apiClient: ApiClient) {

    fun getMovieDetails(movieId: Int) = apiClient.getMovieDetails(movieId)

    fun getMovieCredits(movieId: Int) = apiClient.getMovieCredits(movieId).map { it.cast }

    fun getMovieReviews(movieId: Int) = apiClient.getMovieReviews(movieId).map { it.results }

    fun getRecommendedMovies(movieId: Int) = apiClient.getRecommendedMovies(movieId).map { it.results }

    fun rateMovie(movieId: Int, rating: Float, sessionId: String) = apiClient.rateMovie(movieId, RateBody(rating), sessionId)
}