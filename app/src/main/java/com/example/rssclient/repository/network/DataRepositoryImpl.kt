package com.example.rssclient.repository.network

import com.example.rssclient.network.Controller
import me.toptas.rssconverter.RssFeed


class DataRepositoryImpl(private val controller: Controller):
    RssRepository {

    override suspend fun getData(query:String): RssFeed =
        controller.getData(query).await()

}
