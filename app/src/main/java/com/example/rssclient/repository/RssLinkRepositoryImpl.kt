package com.example.rssclient.repository

import androidx.lifecycle.LiveData

import com.example.rssclient.dataBase.AppDatabase
import com.example.rssclient.dataBase.model.RssLink
import kotlinx.coroutines.*

class RssLinkRepositoryImpl(val database: AppDatabase):RssLinkRepository {

    override fun insertRssLink(rssLink: RssLink) {
        CoroutineScope(Dispatchers.IO).launch {
            database.rssLinkDao().insert(rssLink)
        }
    }

    override fun removeRssLink(rssLink: RssLink) {
        CoroutineScope(Dispatchers.IO).launch {
            database.rssLinkDao().delete(rssLink)
        }
    }

    override fun insertRssLinks(rssLinks: Iterable<RssLink>) {
        CoroutineScope(Dispatchers.IO).launch {
            database.rssLinkDao().insert(rssLinks)
        }
    }

    override fun getAllRssLink(): LiveData<List<RssLink>> {
        return database.rssLinkDao().getAll()
    }
}