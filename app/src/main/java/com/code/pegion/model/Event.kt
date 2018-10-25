package com.code.pegion.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.icu.util.Calendar

@Entity(tableName = "events")
class Event(
        @ColumnInfo(name = "title")
        private var title: String,
        @ColumnInfo(name = "content")
        private var content: String,
        @ColumnInfo(name = "calender")
        val calender: Calendar, val until: Calendar) : Notifiable {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private var id = Notifiable.idGenerator++

    override fun getTitle(): String = title

    override fun setTitle(title: String) {
        this.title = title
    }

    override fun getContent(): String = content

    override fun setContent(content: String) {
        this.content = content
    }

    override fun getDate(): Calendar = calender

    override fun getId(): Int = id

    fun setId(id: Int) {
        this.id = id
    }
}