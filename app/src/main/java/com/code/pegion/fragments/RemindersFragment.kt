package com.code.pegion.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.code.pegion.adapters.ReminderAdapter
import com.code.pegion.viewmodels.RemindersViewModel
import org.jetbrains.anko.matchParent

class RemindersFragment : Fragment() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mReminderViewModel: RemindersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRecyclerView = RecyclerView(activity!!).apply {
            layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)

            (layoutParams as? LinearLayout.LayoutParams)?.apply {
                setMargins(8, 8, 8, 0)
            }
        }

        return mRecyclerView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mReminderViewModel = ViewModelProviders.of(activity!!).get(RemindersViewModel::class.java)
        mRecyclerView.adapter = ReminderAdapter(mReminderViewModel.getReminders().value?.toList()!!)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
    }
}