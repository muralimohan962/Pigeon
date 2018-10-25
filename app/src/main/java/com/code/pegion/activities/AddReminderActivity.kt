package com.code.pegion.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.support.design.button.MaterialButton
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.code.pegion.R
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.linkTextColor
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class AddReminderActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mAddBtn: MaterialButton
    private lateinit var mDateTextView: TextView
    private lateinit var mTimeTextView: TextView
    private lateinit var mTitleTextView: TextInputEditText
    private lateinit var mCommentTextView: TextInputEditText
    private var mCalender = Calendar.getInstance()
    private lateinit var mSetDate: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_reminder_layout)
        initUI()
    }

    private fun initUI() {
        val toolBar = findViewById<Toolbar>(R.id.toolBar)
        toolBar.title = resources.getString(R.string.app_name)
        toolBar.run {
            backgroundColor = Color.parseColor("#00A5F7")
            setTitleTextColor(Color.WHITE)
        }

        setSupportActionBar(toolBar)
        mAddBtn = findViewById<MaterialButton>(R.id.addButton)
        mDateTextView = findViewById(R.id.dateTextView)
        mTimeTextView = findViewById(R.id.timeTextView)
        mTitleTextView = findViewById(R.id.titleTextView)
        mCommentTextView = findViewById(R.id.commentTextView)

        mDateTextView.text = getDisplayDate(mCalender)
        mTimeTextView.text = getDisplayTime(mCalender)

        mSetDate = Calendar.getInstance()
        mAddBtn.onClick {
            addReminder(mSetDate)
        }

        mTitleTextView.linkTextColor = Color.parseColor("#F91899")
        mDateTextView.setOnClickListener(this)
        mTimeTextView.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v == null) return

        when (v as TextView) {
            mDateTextView -> {
                val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    mDateTextView.text = getDisplayDate(Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    })
                    mSetDate.set(year, month, dayOfMonth)
                }, Calendar.getInstance()[Calendar.YEAR], Calendar.getInstance()[Calendar.MONTH], Calendar.getInstance()[Calendar.DAY_OF_MONTH])

                datePickerDialog.show()
            }

            mTimeTextView -> {
                val timePickerDialog = TimePickerDialog(
                        this,
                        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                            mTimeTextView.text = getDisplayTime(Calendar.getInstance().apply {
                                set(Calendar.HOUR_OF_DAY, hourOfDay)
                                set(Calendar.MINUTE, minute)
                            })
                            mSetDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
                            mSetDate.set(Calendar.MINUTE, minute)
                        },
                        Calendar.getInstance()[Calendar.HOUR],
                        Calendar.getInstance()[Calendar.MINUTE],
                        true
                )

                timePickerDialog.show()
            }
        }
    }

    private fun addReminder(calender: Calendar) {
        if (calender.isGreateThan(mCalender))
            toast("Will be added!")
    }

    private fun getDisplayDate(calender: Calendar): String {
        val date = StringBuilder()

        date.append(calender[Calendar.DAY_OF_MONTH])
        val month = calender[Calendar.MONTH]

        val monthText = when (month) {
            0 -> "Jan"
            1 -> "Feb"
            2 -> "Mar"
            3 -> "Apr"
            4 -> "May"
            5 -> "Jun"
            6 -> "Jul"
            7 -> "Aug"
            8 -> "Sep"
            9 -> "Oct"
            10 -> "Nov"
            else -> "Dec"
        }

        date.append(" $monthText ")
        date.append(calender[Calendar.YEAR])

        return date.toString()
    }

    private fun getDisplayTime(calender: Calendar): String {
        val time = StringBuilder()
        val hour = calender[Calendar.HOUR_OF_DAY]
        val minute = calender[Calendar.MINUTE]
        val amOrPm = if (hour in 13..24) "PM" else "AM"

        val hourText = if (hour in 13..24) hour - 12 else hour

        return "$hourText : $minute $amOrPm"
    }
}

fun Calendar.isGreateThan(other: Calendar): Boolean {
    return get(Calendar.DAY_OF_MONTH) > other[Calendar.DAY_OF_MONTH] || get(Calendar.HOUR_OF_DAY) > other[Calendar.HOUR_OF_DAY] ||
            get(Calendar.MINUTE) > other[Calendar.MINUTE]
}