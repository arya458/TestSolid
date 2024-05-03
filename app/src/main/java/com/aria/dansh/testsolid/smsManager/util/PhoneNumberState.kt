package com.aria.dansh.testsolid.smsManager.util

sealed class PhoneNumberState<T>(
    val msg: String? = null
) {
    class Success<T>(msg: String) : PhoneNumberState<T>(msg = msg)

    class ToShort<T>(msg: String) : PhoneNumberState<T>(msg = msg)

    class NumberOnly<T>(msg: String) : PhoneNumberState<T>(msg = msg)

}