package com.aria.dansh.testsolid.smsManager.util

sealed class MessageState<T>(
    val data: T? = null,
    val errorMsg: String? = null
) {
    class Success<T>(data: T?) : MessageState<T>(data = data)

    class Error<T>(msg: String) : MessageState<T>(errorMsg = msg)

    class Loading<T> : MessageState<T>()
}