package com.code.pegion.ui

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.code.pegion.R

class AddReminderDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_reminder_dialog_layout, container, false)
    }


    companion object {
        const val TAG = "AddReminderDialog"
    }
}