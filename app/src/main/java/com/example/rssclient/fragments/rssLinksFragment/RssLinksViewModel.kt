package com.example.rssclient.fragments.rssLinksFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rssclient.App
import com.example.rssclient.dataBase.AppDatabase
import com.example.rssclient.dataBase.model.RssLink
import com.example.rssclient.repository.local.RssLinkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.URL

class RssLinksViewModel : ViewModel() {
    private val db: AppDatabase? = App.instance?.database
    private val dbRepository = RssLinkRepository(db!!)
    private val scope = CoroutineScope(Dispatchers.IO)
    private lateinit var allRssLinks: List<RssLink>
    var allRssLinksLiveData = MutableLiveData<List<RssLink>>()


    fun addRssLink(rssLink: RssLink) {
        scope.launch {
            dbRepository.insertRssLink(rssLink)
        }
    }

    fun removeRssLink(rssLink: RssLink) {
        scope.launch {
            dbRepository.removeRssLink(rssLink)
        }
    }

    fun getAllRssLinks(): MutableLiveData<List<RssLink>> {
        scope.launch {
            allRssLinks = dbRepository.getAllRssLink()
            allRssLinksLiveData.postValue(allRssLinks)
        }
        return allRssLinksLiveData
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
