package com.example.rssclient.fragments.feedFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class FeedViewModelFactory(val id: Long, val link:String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedViewModel(id, link) as T
    }
}