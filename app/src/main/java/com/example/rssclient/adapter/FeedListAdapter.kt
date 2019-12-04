package com.example.rssclient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssclient.R
import com.example.rssclient.dataBase.model.RssLink
import com.example.rssclient.databinding.FeedListItemBinding
import me.toptas.rssconverter.RssItem

class FeedListAdapter(private var listener: OnItemClickListener) :
    RecyclerView.Adapter<FeedListAdapter.DataHolder>() {

    interface OnItemClickListener {
        fun onItemClick(data: RssItem)
    }

    private var dataList: List<RssItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.feed_list_item, parent, false)
        val binding = FeedListItemBinding.bind(itemView)
        return DataHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setList(itemList: List<RssItem>) {
        dataList = itemList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val currentNote = dataList[position]
        holder.binding(currentNote, listener)
    }

    inner class DataHolder(private var binding: FeedListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(dataItem: RssItem, listener: OnItemClickListener?) {
            binding.rssFeedItem = dataItem
            if (listener != null) {
                binding.root.setOnClickListener { _ -> listener.onItemClick(dataList[layoutPosition]) }
            }
            binding.executePendingBindings()
        }
    }

}