package com.example.rssclient.fragments.feedFragment

import android.util.Log
import androidx.lifecycle.*
import com.example.rssclient.network.Controller
import com.example.rssclient.repository.network.DataRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.toptas.rssconverter.RssFeed
import java.lang.Exception

class FeedViewModel : ViewModel() {
    private val repository: DataRepositoryImpl
    private var rssFeed: RssFeed = RssFeed()
    private val rssFeedEvent = MutableLiveData<RssFeed>()
    private var isDataLoaded = false
    private val scope = CoroutineScope(Dispatchers.IO)


    init {
        val controller = Controller()
        repository = DataRepositoryImpl(controller)
    }

    fun getData(query: String): MutableLiveData<RssFeed> {
        if (!isDataLoaded) {
            isDataLoaded = true
            scope.launch {
                try {
                    rssFeed = repository.getData(query)
                    rssFeedEvent.postValue(rssFeed)
                } catch (e: Exception) {
                    Log.d("vm", e.message)
                }
            }
        } else {
            if (rssFeed.items != null) {
                rssFeedEvent.postValue(rssFeed)
            }
        }
        return rssFeedEvent
    }
}
