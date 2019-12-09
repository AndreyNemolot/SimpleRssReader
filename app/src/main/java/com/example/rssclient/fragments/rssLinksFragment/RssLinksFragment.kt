package com.example.rssclient.fragments.rssLinksFragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.rssclient.R
import com.example.rssclient.activity.MainActivity
import com.example.rssclient.adapter.RssLinkAdapter
import com.example.rssclient.adapter.SwipeToDeleteCallback
import com.example.rssclient.dataBase.model.RssLink
import com.example.rssclient.databinding.RssLinksFragmentBinding
import com.example.rssclient.model.LinkVerifyStatus
import com.example.rssclient.showToast
import kotlinx.android.synthetic.main.rss_links_fragment.*


class RssLinksFragment : Fragment(), RssLinkAdapter.OnItemClickListener,
    CreateLinkDialogClickListener {

    private lateinit var adapter: RssLinkAdapter
    private lateinit var binding: RssLinksFragmentBinding
    private lateinit var viewModel: RssLinksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RssLinksFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerViewAdapter()
        setViewModel()
        binding.fragment = this

        viewModel.getAllRssLinks().observe(this, Observer {
            adapter.setList(it)
        })


    }


    private fun setViewModel() {
        viewModel = ViewModelProviders.of(this).get(RssLinksViewModel::class.java)
    }

    private fun setRecyclerViewAdapter() {
        adapter = RssLinkAdapter(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
        setSwipeHandler()
    }

    private fun setSwipeHandler() {
        val swipeHandler = object : SwipeToDeleteCallback(activity!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as RssLinkAdapter
                viewModel.removeRssLink(adapter.getItemByPosition(viewHolder.adapterPosition))
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    fun showDialogForAddRssLink() {
        val dialog = Dialog()
        val fm = fragmentManager
        dialog.show(fm!!, getString(R.string.dialog_fragment_tag))
    }

    override fun onItemClick(data: RssLink) {
        setFeedFragment(data)
    }

    private fun setFeedFragment(data: Parcelable) {
        (activity as MainActivity).setFeedFragment(
            getString(R.string.feed_fragment_tag),
            data
        )
    }

    override fun onClick(name: String, link: String) {
        when (viewModel.verifyAddedLink(name, link)) {
            LinkVerifyStatus.EMPTY_NAME -> {
                showToast(getString(R.string.empty_link_name))
            }
            LinkVerifyStatus.WRONG_URL -> {
                showToast(getString(R.string.url_not_valid))
            }
            LinkVerifyStatus.SUCCESS -> {
                val newRssLink = RssLink(0, name, link)
                viewModel.addRssLink(newRssLink)
                showToast("Added $name")
            }
        }
    }
}
