package com.aria.dansh.testsolid.smsManager.presentation.viewmodel

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aria.dansh.testsolid.smsManager.domain.model.MessageDTO
import com.aria.dansh.testsolid.smsManager.domain.repository.MessengerRepository
import com.aria.dansh.testsolid.smsManager.util.MessageState
import com.aria.dansh.testsolid.smsManager.util.PhoneNumberState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


//Todo:: ViewModel Here

@HiltViewModel
class SmsManagerViewModel @Inject constructor(
    private val messageRepository: MessengerRepository,
) : ViewModel() {


    private val _sendMessageState: MutableStateFlow<MessageState<String>> =
        MutableStateFlow(MessageState.Loading())
    var sendMessageState: StateFlow<MessageState<String>> = _sendMessageState
    private val _phoneNumberState: MutableStateFlow<PhoneNumberState<String>> =
        MutableStateFlow(PhoneNumberState.ToShort(""))
    var phoneNumberState: StateFlow<PhoneNumberState<String>> = _phoneNumberState
    private val _phoneNumber: MutableState<String> = mutableStateOf("")

    init {
        getMessage()
    }

    fun sendMessage(phoneNumber: String, textMessage: String) {
        viewModelScope.launch(Dispatchers.IO) {
            messageRepository.sendMessage(MessageDTO(phoneNumber, textMessage))
                .collectLatest { state ->
                    _sendMessageState.value = state
                    _phoneNumber.value = phoneNumber
                }
        }
    }

    private fun getMessage() {
        GlobalScope.launch(Dispatchers.Main) {
            val messages = messageRepository.getMessage()
            messages.observeForever { messagesArray ->
                for (message in messagesArray) {
                    if (message.originatingAddress != null)
                        if (message.originatingAddress!!.contains(
                                _phoneNumber.value.replace(
                                    "0",
                                    ""
                                )
                            )
                        ) {
                            messageRepository.sendNotification(
                                MessageDTO(
                                    message.originatingAddress.toString(),
                                    message.messageBody.toString()
                                )
                            )
                        }
                }
            }
        }
    }

    private fun checkPhoneNumber(phoneNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (Patterns.PHONE.matcher(phoneNumber).matches())
                if (phoneNumber[0] == '0')
                    if (phoneNumber.length == 11)
                        _phoneNumberState.emit(PhoneNumberState.Success("Looks Good"))
                    else
                        _phoneNumberState.emit(PhoneNumberState.Success("Phone Number Is To Short"))
                else if (phoneNumber[0] == '+')
                    if (phoneNumber.length == 13)
                        _phoneNumberState.emit(PhoneNumberState.Success("Looks Good"))
                    else
                        _phoneNumberState.emit(PhoneNumberState.Success("Phone Number Is To Short"))
                else
                    _phoneNumberState.emit(PhoneNumberState.NumberOnly("Phone Number Should Contain Numbers ONLY"))

        }
    }

}