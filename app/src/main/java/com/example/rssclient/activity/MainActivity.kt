package com.example.rssclient.activity

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.rssclient.FragmentBuilder
import com.example.rssclient.R
import com.example.rssclient.fragments.feedFragment.FeedFragment
import com.example.rssclient.fragments.rssLinksFragment.RssLinksFragment
import com.example.rssclient.stringFromResources

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private val rssLinksFragment = RssLinksFragment()
    private val feedFragment = FeedFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewModel()
        setLinksFragment()
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }


    private fun setLinksFragment() {
        FragmentBuilder(
            supportFragmentManager,
            rssLinksFragment, viewModel.currentFragmentTag
        )
            .commit()
    }


    fun setFeedFragment(tag: String, data: Parcelable) {
        viewModel.currentFragmentTag = tag

        FragmentBuilder(
            supportFragmentManager,
            FeedFragment(), viewModel.currentFragmentTag
        )
            .addToBackStack()
            .setParcelableData(stringFromResources(R.string.key_rss_link), data)
            .commit()
    }

}
