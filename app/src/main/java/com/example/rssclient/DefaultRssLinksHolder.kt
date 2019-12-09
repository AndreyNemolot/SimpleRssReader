package com.example.rssclient

import android.content.Context
import com.example.rssclient.dataBase.model.RssLink
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.InputStream

class DefaultRssLinksHolder(private val context: Context) {
    private var defaultLinks = emptyList<RssLink>()

    init {
        initList()
    }

    private fun initList() {
        val str = readJSONFromAsset()
        if (str != "") {
            val mapper = jacksonObjectMapper()
            defaultLinks = mapper.readValue(
                str, mapper.typeFactory.constructCollectionType(
                    MutableList::class.java,
                    RssLink::class.java
                )
            )
        }
    }

    private fun readJSONFromAsset(): String {
        var json = ""
        try {
            val inputStream: InputStream = context
                .assets.open(context.getString(R.string.default_links_file))
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return json
        }
        return json
    }

    fun getCityList(): List<RssLink> {
        if (defaultLinks.isNullOrEmpty()) {
            initList()
        }
        return defaultLinks
    }

}