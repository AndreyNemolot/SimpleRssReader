package com.example.rssclient.repository.local

import androidx.room.withTransaction
import com.example.rssclient.dataBase.AppDatabase
import com.example.rssclient.dataBase.model.RssLink

class RssLinkRepository(val database: AppDatabase) {

    suspend fun insertRssLink(rssLink:RssLink){
        database.withTransaction{
            database.rssLinkDao().insert(rssLink)
        }
    }

    suspend fun removeRssLink(rssLink:RssLink){
        database.withTransaction{
            database.rssLinkDao().delete(rssLink)
        }
    }

    suspend fun getAllRssLink():List<RssLink>{
        var listRssLinks:List<RssLink> = ArrayList<RssLink>()
        database.withTransaction{
            listRssLinks= database.rssLinkDao().getAll()
        }

        return listRssLinks
    }
}