package com.shykun.volodymyr.movieviewer.presentation.base

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.shykun.volodymyr.movieviewer.data.entity.MoviesType
import com.shykun.volodymyr.movieviewer.data.entity.TvType
import com.shykun.volodymyr.movieviewer.presentation.discover.filter.FILTER_LIST_FRAGMENT_KEY
import com.shykun.volodymyr.movieviewer.presentation.discover.filter.FilterListFragment
import com.shykun.volodymyr.movieviewer.presentation.discover.filter.FilterType
import com.shykun.volodymyr.movieviewer.presentation.discover.list.DISCOVER_LIST_FRAGMENT_KEY
import com.shykun.volodymyr.movieviewer.presentation.discover.list.DiscoverListFragment
import com.shykun.volodymyr.movieviewer.presentation.discover.tab.DISCOVER_FRAGMENT_KEY
import com.shykun.volodymyr.movieviewer.presentation.discover.tab.DiscoverFragment
import com.shykun.volodymyr.movieviewer.presentation.movies.details.MOVIE_DETAILS_FRAGMENT_KEY
import com.shykun.volodymyr.movieviewer.presentation.movies.details.MovieDetailsFragment
import com.shykun.volodymyr.movieviewer.presentation.movies.list.MOVIE_LIST_FRAGMENT_KEY
import com.shykun.volodymyr.movieviewer.presentation.movies.list.MovieListFragment
import com.shykun.volodymyr.movieviewer.presentation.movies.tab.MOVIE_TAB_FRAGMENT_KEY
import com.shykun.volodymyr.movieviewer.presentation.movies.tab.MovieTabFragment
import com.shykun.volodymyr.movieviewer.presentation.people.details.PERSON_DETAILS_FRAGMENT_KEY
import com.shykun.volodymyr.movieviewer.presentation.people.details.PersonDetailsFragment
import com.shykun.volodymyr.movieviewer.presentation.people.tab.PEOPLE_TAB_FRAGMENT_KEY
import com.shykun.volodymyr.movieviewer.presentation.people.tab.PeopleTabFragment
import com.shykun.volodymyr.movieviewer.presentation.tv.details.TV_DETAILS_FRAGMENT
import com.shykun.volodymyr.movieviewer.presentation.tv.details.TvDetailsFragment
import com.shykun.volodymyr.movieviewer.presentation.tv.list.TV_LIST_FRAGMENT_KEY
import com.shykun.volodymyr.movieviewer.presentation.tv.list.TvListFragment
import com.shykun.volodymyr.movieviewer.presentation.tv.tab.TV_TAB_FRAGMENT_KEY
import com.shykun.volodymyr.movieviewer.presentation.tv.tab.TvTabFragment
import ru.terrakok.cicerone.android.SupportAppNavigator

class FlowNavigator(activity: FragmentActivity, fragmentManager: FragmentManager, containerId: Int) : SupportAppNavigator(activity, fragmentManager, containerId) {
    override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? {
        return null
    }

    override fun createFragment(screenKey: String?, data: Any?): Fragment? {
        return when (screenKey) {
            MOVIE_LIST_FRAGMENT_KEY -> MovieListFragment.newInstance(data as MoviesType)
            TV_LIST_FRAGMENT_KEY -> TvListFragment.newInstance(data as TvType)
            MOVIE_TAB_FRAGMENT_KEY -> MovieTabFragment()
            TV_TAB_FRAGMENT_KEY -> TvTabFragment()
            PEOPLE_TAB_FRAGMENT_KEY -> PeopleTabFragment()
            MOVIE_DETAILS_FRAGMENT_KEY -> MovieDetailsFragment.newInstance(data as Int)
            TV_DETAILS_FRAGMENT -> TvDetailsFragment.newInstance(data as Int)
            PERSON_DETAILS_FRAGMENT_KEY -> PersonDetailsFragment.newInstance(data as Int)
            DISCOVER_FRAGMENT_KEY -> DiscoverFragment()
            DISCOVER_LIST_FRAGMENT_KEY -> DiscoverListFragment()
            FILTER_LIST_FRAGMENT_KEY -> FilterListFragment.newInstance(data as FilterType)
            else -> throw RuntimeException("Unknown key")
        }
    }
}