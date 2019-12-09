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

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private val rssLinksFragment = RssLinksFragment()
    private val feedFragment = FeedFragment()
    private lateinit var linksFragmentTag :String
    private lateinit var feedFragmentTag : String
    private lateinit var fragmentsMap : HashMap<String, Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragmentsMap()
        setViewModel()
        setLinksFragment()
    }

    private fun setFragmentsMap(){
        linksFragmentTag = getString(R.string.rss_fragment_tag)
        feedFragmentTag = getString(R.string.feed_fragment_tag)
        fragmentsMap = hashMapOf(
        linksFragmentTag to rssLinksFragment, feedFragmentTag to feedFragment)
    }

    private fun setViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }


    private fun setLinksFragment() {
        if(!viewModel.isFirst){
            FragmentBuilder(
                supportFragmentManager,
                getString(R.string.rss_fragment_tag),
                fragmentsMap
            ).commit()
            viewModel.isFirst = true
        }
    }

    fun setFeedFragment(tag: String, data: Parcelable) {
        FragmentBuilder(
            supportFragmentManager,
            tag,
            fragmentsMap
        )
            .addToBackStack()
            .setParcelableData(getString(R.string.key_rss_link), data)
            .commit()
    }
}
