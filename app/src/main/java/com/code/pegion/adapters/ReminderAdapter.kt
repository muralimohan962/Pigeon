package com.code.pegion.adapters

import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.code.pegion.R
import com.code.pegion.model.Reminder
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class ReminderAdapter(reminders: List<Reminder> = emptyList()) : RecyclerView.Adapter<ReminderAdapter.MyViewHolder>() {
    private var mReminders = arrayListOf<Reminder>(*reminders.toTypedArray())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reminder_view_layout, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = mReminders.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.run {
            findViewById<TextView>(R.id.titleView).text = mReminders[position].getTitle()
            findViewById<TextView>(R.id.contentView).text = mReminders[position].getContent()
            findViewById<ImageButton>(R.id.optionBtn).run {
                onClick { view ->
                    val popupMenu = PopupMenu(context, this@run)
                    popupMenu.menuInflater.inflate(R.menu.reminder_item_menu, popupMenu.menu)
                    popupMenu.setOnMenuItemClickListener {
                        handle(it.title.toString(), position, view!!)
                        true
                    }
                    popupMenu.show()
                }
            }
        }

    }

    fun updateDataSet(reminders: List<Reminder>) {
        mReminders = ArrayList(reminders)
        notifyDataSetChanged()
    }

    private fun handle(text: String, position: Int, view: View) {
        when (text) {
            "Share" -> view.context.toast("Sharing....")
            "Delete" -> {
                mReminders.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}