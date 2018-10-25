package com.code.pegion.ui

import android.graphics.Color
import android.support.design.widget.TextInputLayout
import android.view.View
import android.view.ViewManager
import android.widget.LinearLayout
import com.code.pegion.activities.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        verticalLayout {
            orientation = LinearLayout.VERTICAL
            textInputLayoutView().apply {
                editText?.run {
                    hint = "Title"
                    hintTextColor = Color.parseColor("#F12F6A")
                }
            }
            textInputLayoutView().apply {
                editText?.run {
                    hint = "Comment"
                    hintTextColor = Color.parseColor("#F12F6A")
                }
            }
        }
    }
}

private fun ViewManager.textInputLayoutView() = textInputLayout {}

private fun ViewManager.textInputLayout(init: TextInputLayout.() -> Unit): TextInputLayout {
    return ankoView({ TextInputLayout(it) }, theme = 0, init = init)
}