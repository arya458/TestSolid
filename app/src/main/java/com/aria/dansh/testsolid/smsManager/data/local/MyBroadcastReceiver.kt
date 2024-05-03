package com.aria.dansh.testsolid.smsManager.data.local

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject



class MyBroadcastReceiver @Inject constructor(): BroadcastReceiver() {

    private val _data: MutableLiveData<Array<SmsMessage>> = MutableLiveData<Array<SmsMessage>>()
    val data: LiveData<Array<SmsMessage>> = _data

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
                val smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
                _data.value = smsMessages
        }
    }

}
