package com.example.rssclient.network


import me.toptas.rssconverter.RssConverterFactory
import me.toptas.rssconverter.RssFeed
import retrofit2.Call
import retrofit2.Retrofit


class Controller() {
    private val BASE_URL = "https://github.com"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(RssConverterFactory.create())
        .build()


    fun getData(rssUrl: String): Call<RssFeed> {
        val service = retrofit.create(RssService::class.java)
        return service.getRss(rssUrl)
    }

}