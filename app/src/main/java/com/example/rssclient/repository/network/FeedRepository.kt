package com.example.rssclient.repository.network

import me.toptas.rssconverter.RssFeed

interface FeedRepository {
    suspend fun getData(query: String): RssFeed
}