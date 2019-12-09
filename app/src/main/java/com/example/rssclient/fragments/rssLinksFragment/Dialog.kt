package com.example.rssclient.fragments.rssLinksFragment

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.rssclient.R
import com.example.rssclient.databinding.AddLinkDialogBinding

class Dialog :
    DialogFragment() {

    private lateinit var listener: CreateLinkDialogClickListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it!!)
            val inflater = requireActivity().layoutInflater;
            builder.setTitle(getString(R.string.add_rss_link_dialog_text))
            val dialogLayout = inflater.inflate(R.layout.add_link_dialog, null)
            val dialogBinding = AddLinkDialogBinding.bind(dialogLayout)

            builder.setView(dialogLayout)
                .setPositiveButton(
                    getString(R.string.add_new_rss_link)
                ) { _, _ ->
                    val nameText = dialogBinding.name.text.toString()
                    val linkText = dialogBinding.link.text.toString()
                    listener.onClick(nameText, linkText)
                }

            builder.create()
        }
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        try {
            val fragment = fragmentManager!!.findFragmentByTag(getString(R.string.rss_fragment_tag))
            listener = fragment as RssLinksFragment
        } catch (e: ClassCastException) {
            throw ClassCastException(
                (context.toString() +
                        " must implement NoticeDialogListener")
            )
        }
    }
}

interface CreateLinkDialogClickListener {
    fun onClick(name: String, link: String)
}