package com.example.rssclient

import android.content.Context
import android.os.Parcelable
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Context.showToast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, duration).show()
}

fun Context.stringFromResources(resourceId: Int): String {
    return resources.getString(resourceId)
}

fun Fragment.stringFromResources(resourceId: Int): String {
    return resources.getString(resourceId)
}

fun Fragment.replaceFragment(id: Int, fragment: Fragment, data: Parcelable) {
    val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
    fragmentTransaction.addToBackStack(null)
    fragmentTransaction.replace(id, fragment)
    fragmentTransaction.commit()
}