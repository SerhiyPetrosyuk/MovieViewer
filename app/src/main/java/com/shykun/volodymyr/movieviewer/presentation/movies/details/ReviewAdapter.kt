package com.shykun.volodymyr.movieviewer.presentation.movies.details

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shykun.volodymyr.movieviewer.R
import com.shykun.volodymyr.movieviewer.data.entity.Review
import com.shykun.volodymyr.movieviewer.databinding.ViewHolderReviewItemBinding
import com.shykun.volodymyr.movieviewer.presentation.base.BaseRecyclerViewAdapter

class ReviewAdapter(items: ArrayList<Review>) : BaseRecyclerViewAdapter<Review, ReviewViewHolder>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewHolderReviewItemBinding>(
                inflater,
                R.layout.view_holder_review_item,
                parent,
                false)
        return ReviewViewHolder(binding)
    }

    fun addReviews(reviews: List<Review>) {
        items.addAll(reviews)
        notifyDataSetChanged()
    }
}