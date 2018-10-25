package com.code.pegion.model

import android.icu.util.Calendar

interface Notifiable {

    fun getTitle(): String

    fun setTitle(title: String)

    fun getContent(): String

    fun setContent(content: String)

    fun getDate(): Calendar

    fun getId(): Int

    companion object {
        var idGenerator = 0
    }
}