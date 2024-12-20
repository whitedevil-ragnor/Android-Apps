package com.meow.whisper.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.meow.whisper.R
import com.meow.whisper.dataClasses.Message
import com.meow.whisper.dataClasses.User
import com.meow.whisper.repositories.AuthRepository
import com.meow.whisper.repositories.UserRepository
import com.meow.whisper.viewModels.AuthViewModel
import com.meow.whisper.viewModels.UserViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")


@Composable
fun UserScreen(navController: NavController, authRepository: AuthRepository, userRepository: UserRepository) {



    var search by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }
    val flagAddMember = remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf("") }
    var userPhn by remember { mutableStateOf("") }

    if (flagAddMember.value) {
        AlertDialog(
            shape = RoundedCornerShape(10.dp),
            backgroundColor = colorResource(R.color.splash_background),
            onDismissRequest = {
                flagAddMember.value = false
            },
            title = {
                Text(
                    text = "Add Friend",
                    modifier = Modifier.padding(bottom = 16.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("", Modifier.size(16.dp))
                    OutlinedTextField(
                        value = userName,
                        onValueChange = { userName = it },
                        placeholder = { Text("Name", color = Color.Gray) },
                        leadingIcon = {
                            Icon(Icons.Default.Info, contentDescription = "Info Icon")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Color.Black,
                            backgroundColor = Color.White,
                            focusedBorderColor = Color.DarkGray,
                            unfocusedBorderColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = userPhn,
                        onValueChange = { userPhn = it },
                        placeholder = { Text("Mobile Number", color = Color.Gray) },
                        leadingIcon = {
                            Icon(Icons.Default.Call, contentDescription = "Call Icon")
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Color.Black,
                            backgroundColor = Color.White,
                            focusedBorderColor = Color.DarkGray,
                            unfocusedBorderColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(8.dp),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(
                        onClick = {
                            // Logic to add friend using userViewModel here
                            //userViewModel.addFriend(userName, userPhn)
                            flagAddMember.value = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.black),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Add", fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = {
                            flagAddMember.value = false
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.black),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Cancel")
                    }
                }
            }
        )
    }

    LaunchedEffect(Unit) {
        // Any initial data load if necessary
    }

    val topBar: @Composable () -> Unit = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(R.color.splash_background)
            ),
            title = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    OutlinedTextField(
                        value = search,
                        onValueChange = { search = it },
                        placeholder = {
                            Text(
                                text = "Search",
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        },
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                        ),
                        modifier = Modifier
                            .fillMaxWidth(0.75f)
                            .height(56.dp)
                            .padding(horizontal = 8.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.Gray
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            },
            actions = {
                IconButton(onClick = { showMenu = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = Color.White
                    )
                }
                DropdownMenu(
                    modifier = Modifier.background(colorResource(R.color.splash_background)),
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false }) {
                    DropdownMenuItem(onClick = {
                        showMenu = false
                        flagAddMember.value = true
                    }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.add_member),
                                contentDescription = "Add member",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Add Friend", color = Color.White)
                        }
                    }
                    DropdownMenuItem(onClick = {
                        showMenu = false
                    }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.account_box),
                                contentDescription = "Account",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Account", color = Color.White)
                        }
                    }
                    DropdownMenuItem(onClick = {
                        showMenu = false
                    }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.settings),
                                contentDescription = "Setting",
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Setting", color = Color.White)
                        }
                    }
                }
            }//end
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        topBar = topBar) {
        val user = User(
            name = "Rudra Narayan Rath",
            phoneNumber = "91234445442",
            friends = listOf("Sumanta Kumar Rath", "Chirasmita Rath")
        )
        Column(
            Modifier.fillMaxSize().background(colorResource(R.color.users_background))
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            LazyColumn {
                items(user.friends) { userFriend ->
                    UserItem(userFriend)
                }
            }
        }
    }
}

@Composable
fun UserItem(user: String) {
    Card(
        modifier = Modifier.height(80.dp).fillMaxWidth().padding(top = 8.dp).clickable {
        },
        backgroundColor = colorResource(R.color.splash_background),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(Modifier.fillMaxSize()) {
            IconButton(
                onClick = {},
                modifier = Modifier.size(100.dp).clip(CircleShape).padding(8.dp)
            ) {
                Icon(Icons.Default.AccountCircle, contentDescription = null,
                    modifier = Modifier.size(100.dp))
            }
            Spacer(Modifier.width(16.dp))
            Column {
                Text(user,
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White)
                Text("recent message",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Gray)
            }
        }
    }
}
