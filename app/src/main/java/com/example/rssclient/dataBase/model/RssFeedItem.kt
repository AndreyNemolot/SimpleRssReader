package com.example.rssclient.dataBase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    foreignKeys = [ForeignKey(
        entity = RssLink::class, parentColumns =["id"],
        childColumns = ["rssLinkId"],
        onDelete = CASCADE
    )]
)
data class RssFeedItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var title: String = "",
    var link: String = "",
    var publishDate: String = "",
    var description: String = "",
    val rssLinkId: Long
) : Parcelable
