package com.aria.dansh.testsolid.smsManager.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aria.dansh.testsolid.smsManager.presentation.viewmodel.SmsManagerViewModel



@Composable
fun App(viewModel: SmsManagerViewModel= hiltViewModel()) {
    val phoneNumber = remember { mutableStateOf("") }
    val textMessage = remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize(),Arrangement.Center,Alignment.CenterHorizontally) {

        TextField(value = phoneNumber.value, onValueChange = {phoneNumber.value = it}, modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(60.dp), placeholder = {
            Text(text = "Phone Number", color = MaterialTheme.colorScheme.onSurface)
        })
        Spacer(modifier = Modifier.size(10.dp))
        TextField(value = textMessage.value, onValueChange = {textMessage.value = it}, modifier = Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.5f), placeholder = {
            Text(text = "Your Message ...", color = MaterialTheme.colorScheme.onSurface)
        })
        Spacer(modifier = Modifier.size(10.dp))
        Button(onClick = { viewModel.sendMessage(phoneNumber.value,textMessage.value) }) {
            Text(text = "Send")
        }



    }
}

@Composable
@Preview()
fun AppTest(){

    App()

}