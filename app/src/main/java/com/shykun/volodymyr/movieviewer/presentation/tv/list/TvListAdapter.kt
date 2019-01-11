package com.shykun.volodymyr.movieviewer.presentation.tv.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.shykun.volodymyr.movieviewer.R
import com.shykun.volodymyr.movieviewer.data.entity.Tv
import com.shykun.volodymyr.movieviewer.data.entity.TvType
import com.shykun.volodymyr.movieviewer.presentation.base.BaseRecyclerViewAdapter

private const val TV = 0
private const val LOADING = 1

class TvListAdapter(itemList: ArrayList<Tv>, val tvType: TvType)
    : BaseRecyclerViewAdapter<Tv, BaseTvListViewHolder>(itemList) {

    var lastLoadedPage = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseTvListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TV)
            TvListViewHolder(inflater.inflate(R.layout.view_holder_movie, parent, false), tvType)
        else
            TvListLoadingViewHolder(inflater.inflate(R.layout.view_holder_loading, parent, false))
    }

    override fun onBindViewHolder(viewHolder: BaseTvListViewHolder, position: Int) {
        if (position < items.size) {
            super.onBindViewHolder(viewHolder, position)
        }
    }

    override fun getItemCount() = items.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position < items.size)
            TV
        else
            LOADING
    }

    fun addTvList(tvList: ArrayList<Tv>) {
        items.addAll(tvList)
        notifyDataSetChanged()

    }
}