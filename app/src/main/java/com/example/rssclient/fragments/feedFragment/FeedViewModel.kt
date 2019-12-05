package com.example.rssclient.fragments.feedFragment

import android.util.Log
import androidx.lifecycle.*
import com.example.rssclient.App
import com.example.rssclient.dataBase.AppDatabase
import com.example.rssclient.dataBase.model.MyRssFeed
import com.example.rssclient.dataBase.model.RssFeedAdapter
import com.example.rssclient.dataBase.model.RssFeedItem
import com.example.rssclient.network.Controller
import com.example.rssclient.repository.FeedRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class FeedViewModel : ViewModel() {
    private val repository: FeedRepositoryImpl
    private var rssFeed: List<RssFeedItem> = ArrayList()
    private var isDataLoaded = false
    private val scope = CoroutineScope(Dispatchers.IO)


    init {
        val controller = Controller()
        val db: AppDatabase? = App.instance?.database
        repository =
            FeedRepositoryImpl(controller, db!!)
    }

    fun getData(itemId: Long, query: String): LiveData<List<RssFeedItem>> {
        val lvData = MutableLiveData<List<RssFeedItem>>()
        scope.launch {
            rssFeed = repository.getData(itemId, query)
            lvData.postValue(rssFeed)
        }
        return lvData
    }

}


