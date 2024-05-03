package com.aria.dansh.testsolid.smsManager.domain.repository

import android.telephony.SmsMessage
import androidx.lifecycle.LiveData
import com.aria.dansh.testsolid.smsManager.domain.model.MessageDTO
import com.aria.dansh.testsolid.smsManager.util.MessageState
import kotlinx.coroutines.flow.Flow

interface MessengerRepository {


    fun registerReceiver()

    fun sendNotification(message:MessageDTO)
    suspend fun getMessage(): LiveData<Array<SmsMessage>>
    suspend fun sendMessage(message : MessageDTO): Flow<MessageState<String>>

    fun createNotificationChannel()

}