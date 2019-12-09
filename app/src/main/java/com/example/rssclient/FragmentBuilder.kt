package com.example.rssclient

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class FragmentBuilder(
    private val fragmentManager: FragmentManager,
    private val fragmentTag: String,
    private val fragmentsMap: HashMap<String, Fragment>
) {
    private val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

    init {
        setFragment()
    }

    private fun setFragment(): FragmentBuilder {
        val fragmentTo = fragmentManager.findFragmentByTag(fragmentTag)
        if (fragmentTo == null || !fragmentTo.isAdded) {
            fragmentTransaction.add(
                R.id.fragmentContainer,
                fragmentsMap[fragmentTag]!!,
                fragmentTag
            )
            if (!fragmentsMap[fragmentTag]!!.isVisible) {
                fragmentTransaction.replace(
                    R.id.fragmentContainer,
                    fragmentsMap[fragmentTag]!!,
                    fragmentTag
                )
            }
        } else {
            fragmentTransaction.replace(R.id.fragmentContainer, fragmentTo, fragmentTag)
        }
        return this
    }

    fun setParcelableData(key: String, data: Parcelable): FragmentBuilder {
        val bundle = Bundle()
        bundle.putParcelable(key, data)
        fragmentsMap[fragmentTag]!!.arguments = bundle
        return this
    }

    fun addToBackStack(): FragmentBuilder {
        fragmentTransaction.addToBackStack(null)
        return this
    }

    fun commit() {
        fragmentTransaction.commit()
    }
}