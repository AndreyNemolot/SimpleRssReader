package com.example.rssclient.repository

import androidx.lifecycle.LiveData
import com.example.rssclient.dataBase.model.RssLink

interface RssLinkRepository {

    fun insertRssLink(rssLink: RssLink)

    fun removeRssLink(rssLink: RssLink)

    fun insertRssLinks(rssLinks: Iterable<RssLink>)

    fun getAllRssLink(): LiveData<List<RssLink>>
}