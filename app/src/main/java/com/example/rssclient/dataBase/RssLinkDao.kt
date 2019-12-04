package com.example.rssclient.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rssclient.dataBase.model.RssLink


@Dao
interface RssLinkDao {
    @Query("SELECT * FROM rsslink")
    fun getAll(): LiveData<List<RssLink>>

    @Query("SELECT * FROM RssLink WHERE id = :id")
    suspend fun getById(id: Long): RssLink

    @Insert
    suspend fun insert(rssLint: RssLink)

    @Insert
    suspend fun insert(rssLint: Iterable<RssLink>)

    @Update
    suspend fun update(rssLint: RssLink)

    @Delete
    suspend fun delete(rssLint: RssLink)

}