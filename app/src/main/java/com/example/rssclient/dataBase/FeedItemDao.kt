package com.example.rssclient.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rssclient.dataBase.model.RssFeedItem


@Dao
interface FeedItemDao {
    @Query("SELECT * FROM RssFeedItem")
    suspend fun getAll(): List<RssFeedItem>

    @Query("SELECT * FROM RssFeedItem where rssLinkId = :rssLinkId")
    suspend fun getListByRssLinkId(rssLinkId:Long): List<RssFeedItem>

    @Query("SELECT * FROM RssFeedItem WHERE id = :id")
    suspend fun getById(id: Long): RssFeedItem

    @Insert
    suspend fun insert(rssFeedLinks: RssFeedItem)

    @Insert
    suspend fun insert(rssFeedLink: List<RssFeedItem>)

    @Update
    suspend fun update(rssFeedLink: RssFeedItem)

    @Delete
    suspend fun delete(rssFeedLink: RssFeedItem)

    @Query("DELETE FROM RssFeedItem WHERE rssLinkId = :rssLinkId")
    suspend fun delete(rssLinkId: Long)

}