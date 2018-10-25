package com.code.pegion.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.Looper
import com.code.pegion.database.NotifiableDatabase
import com.code.pegion.model.Event
import com.code.pegion.utils.debug
import org.jetbrains.anko.toast

class EventsViewModel(application: Application) : AndroidViewModel(application) {
    private val mEvents = MutableLiveData<ArrayList<Event>>().also { it.value = arrayListOf() }
    private val mEventDao = NotifiableDatabase.getInstance(application).getEventDao()

    init {
        loadFromDatabase {
            application.toast("Load completed!")
        }
    }

    fun add(event: Event) {
        if (Looper.myLooper() == Looper.getMainLooper())
            mEvents.value = mEvents.value?.apply { add(event) }
        else
            mEvents.postValue(mEvents.value?.apply { add(event) })
    }

    fun remove(event: Event) {
        if (Looper.myLooper() == Looper.getMainLooper()) mEvents.value = mEvents.value?.apply { remove(event) }
        else mEvents.postValue(mEvents.value?.apply { remove(event) })
    }

    private fun loadFromDatabase(postLoad: () -> Unit) {
        if (doLoad()) postLoad()
    }

    private fun doLoad(): Boolean {
        return try {
            val reminders = mEventDao.query()

            synchronized(mEvents) {
                reminders.forEach { item ->
                    if (mEvents.value?.any { it.getId() != item.getId() }!!) add(item)
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getEvents(): LiveData<ArrayList<Event>> = mEvents

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
        val reminders = mEvents.value?.toList()

        synchronized(mEventDao) {
            reminders?.forEach {
                try {
                    mEventDao.insert(it)
                } catch (e: Exception) {

                }
            }
        }
        postWrite()
    }

    companion object {
        private const val TAG = "EventsViewModel"
    }
}