package com.example.rssclient.fragments.feedFragment

import android.util.Log
import androidx.lifecycle.*
import com.example.rssclient.dataBase.model.MyRssFeed
import com.example.rssclient.dataBase.model.RssFeedAdapter
import com.example.rssclient.network.Controller
import com.example.rssclient.repository.network.FeedRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class FeedViewModel : ViewModel() {
    private val repository: FeedRepositoryImpl
    private var rssFeed: MyRssFeed = MyRssFeed()
    private val rssFeedEvent = MutableLiveData<MyRssFeed>()
    private var isDataLoaded = false
    private val scope = CoroutineScope(Dispatchers.IO)


    init {
        val controller = Controller()
        repository = FeedRepositoryImpl(controller)
    }

    fun getData(itemId: Long, query: String): MutableLiveData<MyRssFeed> {
        if (!isDataLoaded) {
            isDataLoaded = true
            scope.launch {
                try {
                    val tempRssFeed = repository.getData(query)
                    rssFeed = RssFeedAdapter(tempRssFeed, itemId).getMyRssFeed()
                    rssFeedEvent.postValue(rssFeed)
                } catch (e: Exception) {
                    Log.d("vm", e.toString())
                }
            }
        } else {
            rssFeedEvent.postValue(rssFeed)
        }
        return rssFeedEvent
    }
}
