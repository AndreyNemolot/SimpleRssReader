package com.example.rssclient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssclient.R

import com.example.rssclient.dataBase.model.RssFeedItem
import com.example.rssclient.dataBase.model.RssLink
import com.example.rssclient.databinding.FeedListItemBinding


class FeedListAdapter(private var listener: OnItemClickListener) :
    RecyclerView.Adapter<FeedListAdapter.DataHolder>() {

    interface OnItemClickListener {
        fun onItemClick(data: RssFeedItem)
    }

    private var dataList: List<RssFeedItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.feed_list_item, parent, false)
        val binding = FeedListItemBinding.bind(itemView)
        return DataHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setList(itemList: List<RssFeedItem>) {
        dataList = itemList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val currentNote = dataList[position]
        holder.binding(currentNote, listener)
    }

    inner class DataHolder(private var binding: FeedListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(dataItem: RssFeedItem, listener: OnItemClickListener?) {
            binding.rssFeedItem = dataItem
            if (listener != null) {
                binding.root.setOnClickListener { _ -> listener.onItemClick(dataList[layoutPosition]) }
            }
            binding.executePendingBindings()
        }
    }

}