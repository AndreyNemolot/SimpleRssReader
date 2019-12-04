package com.example.rssclient.fragments.rssLinksFragment

import android.R.attr.name
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.rssclient.FragmentBuilder
import com.example.rssclient.R
import com.example.rssclient.activity.MainActivity
import com.example.rssclient.adapter.RssLinkAdapter
import com.example.rssclient.adapter.SwipeToDeleteCallback
import com.example.rssclient.dataBase.model.RssLink
import com.example.rssclient.databinding.AddLinkDialogBinding
import com.example.rssclient.databinding.RssLinksFragmentBinding
import com.example.rssclient.fragments.feedFragment.FeedFragment
import com.example.rssclient.showToast
import com.example.rssclient.stringFromResources
import kotlinx.android.synthetic.main.rss_links_fragment.*
import java.net.URL


class RssLinksFragment : Fragment(), RssLinkAdapter.OnItemClickListener {

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
        val builder = AlertDialog.Builder(activity!!)
        val inflater = layoutInflater
        builder.setTitle(stringFromResources(R.string.add_rss_link_dialog_text))
        val dialogLayout = inflater.inflate(R.layout.add_link_dialog, null)
        val dialogBinding = AddLinkDialogBinding.bind(dialogLayout)

        builder.setView(dialogLayout)
        builder.setPositiveButton(stringFromResources(R.string.add_new_rss_link)) { _, _ ->
            val nameText = dialogBinding.name.text.toString()
            val linkText = dialogBinding.link.text.toString()
            if (!viewModel.isLinkValid(linkText)) {
                showToast("URL not valid")
            } else {
                val newRssLink = RssLink(0, nameText, linkText)
                viewModel.addRssLink(newRssLink)
                Toast.makeText(activity, "Added $nameText", Toast.LENGTH_SHORT).show()
                viewModel.getAllRssLinks()
            }

        }
        builder.show()
    }

    override fun onItemClick(data: RssLink) {
        setFeedFragment(data)
    }

    private fun setFeedFragment(data: Parcelable) {
        (activity as MainActivity).setFeedFragment(stringFromResources(R.string.feed_fragment_tag),data)


    }

}
