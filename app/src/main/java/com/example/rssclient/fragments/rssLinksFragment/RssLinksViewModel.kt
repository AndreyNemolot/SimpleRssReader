package com.example.rssclient.fragments.rssLinksFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rssclient.App
import com.example.rssclient.dataBase.AppDatabase
import com.example.rssclient.dataBase.model.RssLink
import com.example.rssclient.repository.RssLinkRepositoryImpl
import java.lang.Exception
import java.net.URL

class RssLinksViewModel : ViewModel() {
    private val db: AppDatabase? = App.instance?.database
    private val dbRepository =
        RssLinkRepositoryImpl(db!!)

    fun addRssLink(rssLink: RssLink) {
        dbRepository.insertRssLink(rssLink)
    }

    fun removeRssLink(rssLink: RssLink) {
        dbRepository.removeRssLink(rssLink)
    }

    fun getAllRssLinks(): LiveData<List<RssLink>> {
        return dbRepository.getAllRssLink()
    }

    fun isLinkValid(link: String): Boolean {
        return try {
            val u = URL(link)
            u.toURI()
            true
        } catch (e: Exception) {
            false
        }
    }
}
