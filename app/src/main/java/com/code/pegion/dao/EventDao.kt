package com.code.pegion.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.code.pegion.model.Event

/**
 * Object used to access events from the database.
 */
@Dao
interface EventDao {

    @Insert
    fun insert(event: Event)

    @Delete
    fun delete(event: Event)

    @Query("SELECT * FROM events")
    fun query(): Array<Event>
}