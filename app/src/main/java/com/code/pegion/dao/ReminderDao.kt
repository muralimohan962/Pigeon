package com.code.pegion.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.code.pegion.model.Reminder

@Dao
interface ReminderDao {

    @Insert
    fun insert(reminder: Reminder)

    @Delete
    fun delete(reminder: Reminder)

    @Query("SELECT * FROM reminders")
    fun query(): Array<Reminder>
}