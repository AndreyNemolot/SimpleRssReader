package com.example.rssclient

import android.app.Application
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.rssclient.dataBase.AppDatabase
import com.example.rssclient.dataBase.model.RssLink
import com.example.rssclient.repository.local.RssLinkRepository
import kotlinx.coroutines.*


class App : Application() {
    companion object {
        var instance: App? = null
    }

    var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        val scope = CoroutineScope(Dispatchers.Main)
        val rdc: RoomDatabase.Callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                scope.launch {
                    val repo = RssLinkRepository(database!!)
                    repo.insertRssLink(
                        RssLink(
                            0,
                            resources.getString(R.string.default_link_name_1),
                            resources.getString(R.string.default_link_1)
                        )
                    )
                    repo.insertRssLink(
                        RssLink(
                            0,
                            resources.getString(R.string.default_link_name_2),
                            resources.getString(R.string.default_link_2)

                        )
                    )
                    repo.insertRssLink(
                        RssLink(
                            0,
                            resources.getString(R.string.default_link_name_3),
                            resources.getString(R.string.default_link_3)
                        )
                    )
                }
            }
        }

        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .addCallback(rdc)
            .build()
        scope.launch {
            database!!.rssLinkDao().getAll()
        }
    }
}