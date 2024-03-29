package com.example.rssclient.fragments.feedFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.rssclient.R
import com.example.rssclient.adapter.FeedListAdapter
import com.example.rssclient.dataBase.model.RssFeedItem
import com.example.rssclient.dataBase.model.RssLink
import com.example.rssclient.databinding.FeedFragmentBinding


class FeedFragment : Fragment(), FeedListAdapter.OnItemClickListener {

    private lateinit var adapter: FeedListAdapter
    private lateinit var viewModel: FeedViewModel
    private lateinit var binding: FeedFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FeedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setViewModel()
        setRecyclerViewAdapter()

        viewModel.feedItemList.observe(this, Observer {
            adapter.setList(it)
        })
    }

    private fun setViewModel(){
        val rssLink = getRssLink()
        viewModel = ViewModelProviders.of(
            this,
            FeedViewModelFactory(
                rssLink.id,
                rssLink.link
            )
        ).get(FeedViewModel::class.java)
    }

    private fun setRecyclerViewAdapter() {
        adapter = FeedListAdapter(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }

    private fun getRssLink(): RssLink {
        val bundle = this.arguments
        if (bundle != null) {
            val rssLink =
                bundle.getParcelable<RssLink>(getString(R.string.key_rss_link))
            if (rssLink != null) {
                return rssLink
            }
        }
        return RssLink()
    }

    override fun onItemClick(data: RssFeedItem) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.link))
        startActivity(browserIntent)
    }
}
