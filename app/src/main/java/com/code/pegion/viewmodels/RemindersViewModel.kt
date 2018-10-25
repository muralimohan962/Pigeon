package com.code.pegion.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.Looper
import com.code.pegion.database.NotifiableDatabase
import com.code.pegion.model.Reminder
import com.code.pegion.utils.debug
import org.jetbrains.anko.toast

class RemindersViewModel(application: Application) : AndroidViewModel(application) {
    private val mReminders = MutableLiveData<ArrayList<Reminder>>().also { it.value = arrayListOf() }
    private val mReminderDao = NotifiableDatabase.getInstance(application).getReminderDao()

    init {
        loadFromDatabase {
            application.toast("Load completed!")
        }
    }

    fun add(reminder: Reminder) {
        if (Looper.myLooper() == Looper.getMainLooper())
            mReminders.value = mReminders.value?.apply { add(reminder) }
        else
            mReminders.postValue(mReminders.value?.apply { add(reminder) })
    }

    fun remove(reminder: Reminder) {
        if (Looper.myLooper() == Looper.getMainLooper()) mReminders.value = mReminders.value?.apply { remove(reminder) }
        else mReminders.postValue(mReminders.value?.apply { remove(reminder) })
    }

    private fun loadFromDatabase(postLoad: () -> Unit) {
        if (doLoad()) postLoad()
    }

    private fun doLoad(): Boolean {
        return try {
            val reminders = mReminderDao.query()

            synchronized(mReminders) {
                reminders.forEach { item ->
                    if (mReminders.value?.any { it.getId() != item.getId() }!!) add(item)
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getReminders(): LiveData<ArrayList<Reminder>> = mReminders

    override fun onCleared() {
        writeToDatabase {
            getApplication<Application>().toast("Write completed!")
            debug(TAG, "Write completed!")
        }
    }

    private fun writeToDatabase(postWrite: () -> Unit) {
        doWrite(postWrite)
    }

    private fun doWrite(postWrite: () -> Unit) {
        val reminders = mReminders.value?.toList()

        synchronized(mReminderDao) {
            reminders?.forEach {
                try {
                    mReminderDao.insert(it)
                } catch (e: Exception) {
                    debug(TAG, "An exception occurred: ${e.message}.")
                }
            }
        }
        postWrite()
    }

    companion object {
        private const val TAG = "RemindersViewModel"
    }
}