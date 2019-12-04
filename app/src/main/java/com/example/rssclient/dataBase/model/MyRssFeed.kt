package com.example.rssclient.dataBase.model


data class MyRssFeed(
    var items: MutableList<RssFeedItem> = ArrayList<RssFeedItem>()
)