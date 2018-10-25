package com.code.pegion.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.code.pegion.dao.EventDao
import com.code.pegion.dao.ReminderDao
import com.code.pegion.database.typeConverters.CalenderTypeConverter
import com.code.pegion.model.Event
import com.code.pegion.model.Reminder
import com.code.pegion.utils.debug

@Database(entities = [Event::class, Reminder::class], version = 1)
@TypeConverters(CalenderTypeConverter::class)
abstract class NotifiableDatabase : RoomDatabase() {

    abstract fun getReminderDao(): ReminderDao

    abstract fun getEventDao(): EventDao

    companion object {
        private var INSTANCE: NotifiableDatabase? = null
        private const val DB_NAME = "NotifiableDatabase"

        fun getInstance(context: Context): NotifiableDatabase {
            debug("Notifiable", "Creating the database")
            if (INSTANCE == null)
                INSTANCE = build(context)

            return INSTANCE!!
        }

        private fun build(context: Context): NotifiableDatabase {
            return Room.databaseBuilder(context.applicationContext, NotifiableDatabase::class.java, DB_NAME).build()
        }
    }
}