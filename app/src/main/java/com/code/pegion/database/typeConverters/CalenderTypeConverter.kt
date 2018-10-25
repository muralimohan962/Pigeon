package com.code.pegion.database.typeConverters

import android.arch.persistence.room.TypeConverter
import android.icu.util.Calendar
import com.code.pegion.utils.debug
import java.util.*

class CalenderTypeConverter {

    @TypeConverter
    fun toCalender(epoch: Long): Calendar {
        val date = Date(epoch)
        val calender = Calendar.getInstance()
        calender.time = date
        debug("Converter", "Converter accessed!")
        return calender
    }

    @TypeConverter
    fun toEpoch(calendar: Calendar): Long {
        debug("Converter", "Converter accessed!")
        return calendar.time.toInstant().toEpochMilli()
    }
}