package com.hambrech.notificationdemo

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCreate.setOnClickListener { btn ->
            val text = etNotificationText.text.toString()
            scheduleNotification(text)

            btn.isEnabled = false
            btn.postDelayed({
                btn.isEnabled = true
            }, 5000)
        }

    }

    private fun scheduleNotification(text : String) {
        val intent =  Intent(this, AlarmReceiver::class.java)
        intent.putExtra(AlarmReceiver.EXTRA_ID, id++)
        intent.putExtra(AlarmReceiver.EXTRA_TEXT, text)
        intent.action = System.currentTimeMillis().toString()
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager[AlarmManager.RTC, System.currentTimeMillis() + 10000] = pendingIntent
    }
}