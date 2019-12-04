package com.example.rssclient.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.rssclient.R

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var currentFragmentTag: String = application.resources.getString(R.string.rss_fragment_tag)

}