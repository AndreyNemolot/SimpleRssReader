package com.example.rssclient

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.rssclient.dataBase.AppDatabase
import com.example.rssclient.dataBase.model.RssLink
import com.example.rssclient.repository.local.RssLinkRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.*
import java.io.InputStream


class App : Application() {
    companion object {
        var instance: App? = null
    }

    var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        setDefaultDBValues()
    }

    private fun setDefaultDBValues() {
        val scope = CoroutineScope(Dispatchers.Main)
        val defaultLinksList = DefaultRssLinksHolder(this).getCityList()
        val rdc: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                scope.launch {
                    val repo = RssLinkRepository(database!!)
                    repo.insertRssLinks(
                        defaultLinksList
                    )
                }
            }
        }
        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java, stringFromResources(R.string.data_base_name)
        )
            .addCallback(rdc)
            .build()
        scope.launch {
            database!!.rssLinkDao().getAll()
        }
    }

}