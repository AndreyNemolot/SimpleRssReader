package com.example.rssclient.repository.network

import me.toptas.rssconverter.RssFeed

interface RssRepository {
    suspend fun getData(query: String): RssFeed
}