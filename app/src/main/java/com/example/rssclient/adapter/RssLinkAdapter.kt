package com.example.rssclient.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssclient.R
import com.example.rssclient.dataBase.model.RssLink
import com.example.rssclient.databinding.RssListItemBinding

class RssLinkAdapter(private var listener: OnItemClickListener) :
    RecyclerView.Adapter<RssLinkAdapter.DataHolder>() {

    interface OnItemClickListener {
        fun onItemClick(data: RssLink)
    }

    private var dataList: List<RssLink> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rss_list_item, parent, false)
        val binding = RssListItemBinding.bind(itemView)
        return DataHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setList(itemList: List<RssLink>) {
        dataList = itemList
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        (dataList as ArrayList<RssLink>).removeAt(position)
        notifyItemRemoved(position)
    }

    fun getItemByPosition(position: Int): RssLink {
        return (dataList as ArrayList<RssLink>)[position]
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val currentNote = dataList[position]
        holder.binding(currentNote, listener)
    }

    inner class DataHolder(private var binding: RssListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(dataItem: RssLink, listener: OnItemClickListener?) {
            binding.rssLinkItem = dataItem
            if (listener != null) {
                binding.root.setOnClickListener { _ -> listener.onItemClick(dataList[layoutPosition]) }
            }
            binding.executePendingBindings()
        }
    }

}