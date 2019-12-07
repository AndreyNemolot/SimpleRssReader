package com.example.rssclient.fragments.feedFragment

import androidx.lifecycle.*
import com.example.rssclient.App
import com.example.rssclient.dataBase.AppDatabase
import com.example.rssclient.dataBase.model.RssFeedItem
import com.example.rssclient.network.Controller
import com.example.rssclient.repository.FeedRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedViewModel(var itemId: Long, var link: String) : ViewModel() {
    private val repository: FeedRepositoryImpl
    private val scope = CoroutineScope(Dispatchers.IO)
    var rssFeed: List<RssFeedItem> = ArrayList()
    val feedItemList = MutableLiveData<List<RssFeedItem>>()

    init {
        val controller = Controller()
        val db: AppDatabase? = App.instance?.database
        repository =
            FeedRepositoryImpl(controller, db!!)
        getData()
    }

    private fun getData() {
        if (rssFeed.isEmpty()) {
            scope.launch {
                rssFeed = repository.getData(itemId, link)
                feedItemList.postValue(rssFeed)
            }
        } else {
            feedItemList.postValue(rssFeed)
        }
    }

}


