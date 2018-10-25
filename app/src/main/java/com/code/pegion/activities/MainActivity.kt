package com.code.pegion.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.code.pegion.R
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    private lateinit var mToolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mToolBar = findViewById(R.id.toolBar)
        setSupportActionBar(mToolBar)
        mToolBar.title = resources.getString(R.string.app_name)

        startActivity<AddReminderActivity>()
    }
}