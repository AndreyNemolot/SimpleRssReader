package com.example.rssclient.dataBase

import androidx.room.*
import com.example.rssclient.dataBase.model.RssFeedItem


@Dao
interface FeedItemDao {
    @Query("SELECT * FROM RssFeedItem")
    suspend fun getAll(): List<RssFeedItem>

    @Query("SELECT * FROM RssFeedItem WHERE id = :id")
    suspend fun getById(id: Long): RssFeedItem

    @Insert
    suspend fun insert(rssFeedLint: RssFeedItem)

    @Update
    suspend fun update(rssFeedLint: RssFeedItem)

    @Delete
    suspend fun delete(rssFeedLint: RssFeedItem)

}