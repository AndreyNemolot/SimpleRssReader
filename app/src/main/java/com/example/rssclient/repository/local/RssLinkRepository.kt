package com.example.rssclient.repository.local

import androidx.lifecycle.LiveData

import com.example.rssclient.dataBase.AppDatabase
import com.example.rssclient.dataBase.model.RssLink
import kotlinx.coroutines.*

class RssLinkRepository(val database: AppDatabase) {

    fun insertRssLink(rssLink: RssLink) {
        CoroutineScope(Dispatchers.Main).launch {
            database.rssLinkDao().insert(rssLink)
        }
    }

    fun removeRssLink(rssLink: RssLink) {
        CoroutineScope(Dispatchers.Main).launch {
            database.rssLinkDao().delete(rssLink)
        }
    }

    fun insertRssLinks(rssLinks: Iterable<RssLink>) {
        CoroutineScope(Dispatchers.Main).launch {
            database.rssLinkDao().insert(rssLinks)
        }
    }

    fun getAllRssLink(): LiveData<List<RssLink>> {
        return database.rssLinkDao().getAll()
    }
}