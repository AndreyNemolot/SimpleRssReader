package com.example.rssclient.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rssclient.await
import com.example.rssclient.dataBase.AppDatabase
import com.example.rssclient.dataBase.model.RssFeedAdapter
import com.example.rssclient.dataBase.model.RssFeedItem
import com.example.rssclient.network.Controller
import kotlinx.coroutines.*
import me.toptas.rssconverter.RssFeed


class FeedRepositoryImpl(private val controller: Controller, private val database: AppDatabase) :
    FeedRepository {

    override suspend fun getData(itemId: Long, rssUrl: String): List<RssFeedItem> {
        var list: List<RssFeedItem>
        try {
            val tempRssFeed = controller.getData(rssUrl).await()
            list = RssFeedAdapter(tempRssFeed, itemId).getMyRssFeed()
            if (list.isNotEmpty()) {
                setRepositories(itemId, list)
            }
        } catch (e: Exception) {
            list = getLocalData(itemId)
        }
        return list
    }

    override fun setRepositories(rssLinkId: Long, rssFeedRepositories: List<RssFeedItem>) {
        database.runInTransaction {
            CoroutineScope(Dispatchers.IO).launch {
                database.offlineRssItem().delete(rssLinkId)
                database.offlineRssItem().insert(rssFeedRepositories)
            }
        }
    }

    override suspend fun getLocalData(id: Long): List<RssFeedItem> {
        return database.offlineRssItem().getListByRssLinkId(id)

    }

}
