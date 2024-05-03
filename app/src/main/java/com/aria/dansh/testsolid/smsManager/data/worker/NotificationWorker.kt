package com.aria.dansh.testsolid.smsManager.data.worker

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.aria.dansh.testsolid.MainActivity
import com.aria.dansh.testsolid.R
import com.aria.dansh.testsolid.TestSolidApplication
import com.aria.dansh.testsolid.smsManager.util.WorkerDataName

class NotificationWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {

    private val notificationId = 17
    override fun doWork(): Result {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val number = inputData.getString(WorkerDataName.NUMBER.name)
        val textMessage = inputData.getString(WorkerDataName.MESSAGE.name)

        val builder = NotificationCompat.Builder(applicationContext, TestSolidApplication.CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_message_24)
            .setContentTitle(number)
            .setContentText(textMessage)
            .setStyle(NotificationCompat.BigTextStyle().bigText(textMessage))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        Log.d(
            "messageReceiver",
            "Worker is Running "
        )

        with(NotificationManagerCompat.from(applicationContext)) {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                notify(notificationId, builder.build())
            }
        }

        return Result.success()
    }

}

