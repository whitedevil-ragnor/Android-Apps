package com.meow.login_page.screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.meow.login_page.firebaseSetUp.Room
import com.meow.login_page.firebaseSetUp.RoomViewModel


@SuppressLint("CheckResult")
@Composable
fun ChatRoomHomeScreen(roomViewModel: RoomViewModel= viewModel(),onJoinClicked: (Room) -> Unit){
    var showDialog by remember { mutableStateOf(false) }
    var chatRoom by remember { mutableStateOf("") }
   if (showDialog){
        AlertDialog(onDismissRequest = {showDialog=false}, title = {
            Text("Create a room", modifier = Modifier.padding(bottom = 16.dp), fontWeight = FontWeight.SemiBold)
        }, text = {
            TextField(
                modifier = Modifier.fillMaxWidth(), value = chatRoom, onValueChange = {newName->chatRoom=newName}
            )
        },
            confirmButton = {
                Row (modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween){
                    Button(onClick = {
                        if (chatRoom.isNotEmpty()){
                            showDialog=false
                            chatRoom=""
                        }
                    }) {
                        Text("Create Room")
                    }
                    Button(onClick = {showDialog=false;chatRoom=""}) {
                        Text("Cancel")
                    }
                }
            })
    }

    Column (modifier = Modifier.fillMaxSize().statusBarsPadding().background(Color.White)){
        Text("Chat Room", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(16.dp))
        val rooms by roomViewModel.rooms.observeAsState(emptyList())
        LazyColumn {
            items(rooms){room->
                RoomItem(room = room, onJoinClicked = {onJoinClicked(room)})
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            showDialog=true

        },
            modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color.White).shadow(shape = RoundedCornerShape(10.dp), elevation = 30.dp, spotColor = Color.Red),
            colors =ButtonDefaults.buttonColors(Color.Black), shape = RoundedCornerShape(10.dp)
        ) {
            Text("Create Room", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }

    }
}
@Composable
fun RoomItem(room: Room,onJoinClicked:(Room)->Unit){
    Row (modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color.LightGray),
        horizontalArrangement = Arrangement.SpaceBetween){
        Text(room.name, fontWeight = FontWeight.Normal, fontSize = 20.sp,
            modifier = Modifier.padding(top=10.dp, start = 8.dp).height(40.dp))
        OutlinedButton(onClick = {
            onJoinClicked(room)
        }, shape = MaterialTheme.shapes.medium, modifier = Modifier.padding(end = 8.dp)) {
            Text("Join")
        }
    }
}