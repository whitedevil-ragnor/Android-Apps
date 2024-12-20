package com.meow.login_page.screens.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build

import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meow.login_page.R
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun formatTimeStamp(timestamp:Long):String{
    val msgDateAndTime=LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),ZoneId.systemDefault())
    val now=LocalDateTime.now()
    return when{
        isSameDay(msgDateAndTime,now)-> "today ${formatTime(msgDateAndTime)}"
        isSameDay(msgDateAndTime.plusDays(1),now)->"yesterday ${formatTime(msgDateAndTime)}"
        else->formatDate(msgDateAndTime)
    }
}
@RequiresApi(Build.VERSION_CODES.O)
private fun isSameDay(dateTime1: LocalDateTime, dateTime2: LocalDateTime): Boolean {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return dateTime1.format(formatter) == dateTime2.format(formatter)
}
@RequiresApi(Build.VERSION_CODES.O)
private fun formatTime(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return formatter.format(dateTime)
}
@RequiresApi(Build.VERSION_CODES.O)
private fun formatDate(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("MMM d, yyyy")
    return formatter.format(dateTime)
}


@SuppressLint("ResourceType")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChatMsgItem(message: Message){
    Column (modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalAlignment = if(message.isSentByCurrentUser) Alignment.End else Alignment.Start){
        Box(modifier = Modifier.background(if(message.isSentByCurrentUser) colorResource(R.color.teal_200) else colorResource(R.color.purple_200) ,
            shape = RoundedCornerShape(8.dp)).padding(8.dp)){
            Text(
                text = message.text,
                color = colorResource(R.color.white),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = formatTimeStamp(message.timestamp),
                style = TextStyle(
                    fontSize = 12.sp,
                    color = colorResource(R.color.Gray)
                )
            )
        }
    }
}
