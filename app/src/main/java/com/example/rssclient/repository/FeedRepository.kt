package com.example.rssclient.repository

import androidx.lifecycle.LiveData
import com.example.rssclient.dataBase.model.RssFeedItem
import me.toptas.rssconverter.RssFeed

interface FeedRepository {
    suspend fun getData(itemId: Long, rssUrl: String): List<RssFeedItem>

    fun setRepositories(rssLinkId: Long, rssFeedRepositories: List<RssFeedItem>)

    suspend fun getLocalData(id: Long): List<RssFeedItem>
}