package com.example.rssclient.dataBase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class RssLink(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val linkName: String = "",
    val link: String = ""
) : Parcelable
