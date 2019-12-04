package com.example.rssclient

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class FragmentBuilder(
    private val fragmentManager: FragmentManager,
    fragment: Fragment,
    fragmentTag: String
) {
    private val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
    private var chosenFragment = Fragment()

    init {
        setFragment(fragment, fragmentTag)
    }

    private fun setFragment(fragment: Fragment, to: String): FragmentBuilder {
        val fragmentTo = fragmentManager.findFragmentByTag(to)
        if (fragmentTo == null || !fragmentTo.isAdded) {
            chosenFragment = fragment
            fragmentTransaction.replace(R.id.fragmentContainer, chosenFragment, to)
        } else {
            chosenFragment = fragmentTo
            fragmentTransaction.replace(R.id.fragmentContainer, chosenFragment, to)

        }
        return this
    }


    fun setParcelableData(key: String, data: Parcelable): FragmentBuilder {
        val bundle = Bundle()
        bundle.putParcelable(key, data)
        chosenFragment.arguments = bundle
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