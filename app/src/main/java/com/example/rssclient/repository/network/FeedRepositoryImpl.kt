package com.example.rssclient.repository.network

import com.example.rssclient.await
import com.example.rssclient.network.Controller
import me.toptas.rssconverter.RssFeed


class FeedRepositoryImpl(private val controller: Controller):
    FeedRepository {

    override suspend fun getData(query:String): RssFeed =
        controller.getData(query).await()

}
