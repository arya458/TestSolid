package com.aria.dansh.testsolid.smsManager.data.repository

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import android.provider.Telephony
import android.telephony.SmsManager
import android.telephony.SmsMessage
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.work.OneTimeWorkRequest
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.aria.dansh.testsolid.smsManager.data.local.MyBroadcastReceiver
import com.aria.dansh.testsolid.R
import com.aria.dansh.testsolid.TestSolidApplication.Companion.CHANNEL_ID
import com.aria.dansh.testsolid.smsManager.domain.model.MessageDTO
import com.aria.dansh.testsolid.smsManager.domain.repository.MessengerRepository
import com.aria.dansh.testsolid.smsManager.util.MessageState
import com.aria.dansh.testsolid.smsManager.util.WorkerPramName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

@Singleton
class MessengerRepositoryImpl(
    private val context: Application,
    private val broadcastReceiver: MyBroadcastReceiver,
    private val notificationWorker: OneTimeWorkRequest.Builder
) : MessengerRepository {


    private val phoneNumberLiveData: MutableLiveData<String> = MutableLiveData<String>()

    init {
        createNotificationChannel()
        registerReceiver()
    }

    override fun registerReceiver() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            context.registerReceiver(
                broadcastReceiver,
                IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION),
                Context.RECEIVER_EXPORTED
            )
        else
            context.registerReceiver(
                broadcastReceiver,
                IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
            )

    }

    override suspend fun getMessage(): LiveData<Array<SmsMessage>> {
        return broadcastReceiver.data
    }


    override fun sendNotification(message: MessageDTO) {
        notificationWorker.apply {
            setInputData(
                workDataOf(
                    WorkerPramName.NUMBER.name to message.phoneNumber,
                    WorkerPramName.MESSAGE.name to message.text
                )
            )
            setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
        }

        WorkManager
            .getInstance(context)
            .enqueue(notificationWorker.build())

    }

    override suspend fun sendMessage(message: MessageDTO): Flow<MessageState<String>> {
        return flow<MessageState<String>> {
            emit(MessageState.Loading())
            try {
                // on below line initializing sms manager.
                val smsManager: SmsManager =
                    context.getSystemService(SmsManager::class.java)
                // on below line sending sms
                smsManager.sendTextMessage(message.phoneNumber, null, message.text, null, null)
                // on below line displaying
                emit(MessageState.Success("Message Sent To " + message.phoneNumber))
                phoneNumberLiveData.value = message.phoneNumber
            } catch (e: Exception) {
                // on below line handling error and
                emit(MessageState.Error("Message Error : " + e.message))
            }
        }
            .catch {
                emit(MessageState.Error("Flow Error : " + it.localizedMessage))
            }
    }

    override fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(context, R.string.channel_name)
            val descriptionText = getString(context, R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


}