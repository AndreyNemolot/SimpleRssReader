package com.example.rssclient.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rssclient.dataBase.model.RssLink


@Database(entities = [RssLink::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rssLinkDao(): RssLinkDao
}