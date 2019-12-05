package com.example.rssclient.dataBase.model

import me.toptas.rssconverter.RssFeed
import java.lang.Exception
import java.util.concurrent.Executors

class RssFeedAdapter(private val rssFeed: RssFeed, private val rssLinkId: Long) {
    private var myRssFeed = MyRssFeed()


    fun getMyRssFeed(): List<RssFeedItem> {
        try {
            adapt()
        }catch (e:Exception){ }
        return myRssFeed.items
    }

    private fun adapt() {
        myRssFeed.items = ArrayList()
        if(rssFeed.items!=null){
            for (item in rssFeed.items!!) {
                val myRssFeedItem = RssFeedItem(rssLinkId = rssLinkId)
                myRssFeedItem.description = item.description!!
                myRssFeedItem.link = item.link!!
                myRssFeedItem.publishDate = item.publishDate!!
                myRssFeedItem.title = item.title!!
                myRssFeed.items.add(myRssFeedItem)
            }
        }
    }
}