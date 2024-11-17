package com.meow.chatboatdemo

//noinspection UsingMaterialAndMaterial3Libraries
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meow.chatboatdemo.ui.theme.Purple80

@Composable
fun ChatPage(modifier: Modifier=Modifier,viewModel: ChatViewModel){
    Column (modifier=modifier.background(colorResource(R.color.white))){
        AppHeader()
        MessageList(modifier = Modifier.weight(1f),viewModel.messageList)
        MessageInput(onSendMessage = {
            viewModel.sendMessage(it)
        })
    }
}

@Composable
fun MessageList(modifier: Modifier=Modifier,messageList:List<MessageModel>){
        LazyColumn(
            reverseLayout = true,
            modifier=modifier
        ){
            items(messageList.reversed()){
                MessageBox(messageModel = it)
            }
        }
}
@Composable
fun MessageBox(messageModel: MessageModel) {
    val isModel = messageModel.role == "model"
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (isModel) Arrangement.Start else Arrangement.End // Controls alignment of the Box within the Row
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (isModel) colorResource(R.color.light_Blue) else colorResource(R.color.light_Green)
                )
                .padding(
                    start = if (isModel) 8.dp else 16.dp,
                    end = if (isModel) 16.dp else 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                )
        ) {
            Text(
                text = messageModel.message,
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                color = if (isModel) Color.Black else Color.White,
                modifier = Modifier.padding(12.dp) // Padding for text within the Box
            )
        }
    }
}


@Composable
fun AppHeader(){
    Box(
        modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colors.primary)){
        Text("StudyMate",
            modifier = Modifier.padding(16.dp),
            color = Color.White,
            fontSize = 22.sp)
    }
}
@Composable
fun MessageInput(onSendMessage:(String)->Unit){
    var message by remember { mutableStateOf("") }
    Row (modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.Center){
        OutlinedTextField(
            modifier = Modifier.weight(1f).padding(8.dp),
            value =message , shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color.Black,
                focusedBorderColor = Color.Blue,
                focusedTextColor = Color.Black,
                unfocusedBorderColor = Color.Black),
            onValueChange = {message=it} )
        IconButton(onClick = {
            if (message.isNotEmpty()){
                onSendMessage(message)
                message=""
            }
        },
        ) {
            Icon(
                modifier = Modifier.size(60.dp).padding(top = 8.dp),
                painter = painterResource(R.drawable.send),
                contentDescription = "Send Icon",
                tint = Color.Blue)
        }
    }
}