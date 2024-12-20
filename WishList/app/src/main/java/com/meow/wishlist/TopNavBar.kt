package com.meow.wishlist
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.meow.wishlist.Navigation.Screen
import com.meow.wishlist.R


@Composable
fun TopNavBar(title: String, onBackClick: () -> Unit = {},navController: NavController) {
    var showMenu by remember { mutableStateOf(false) }
    val naviagationIcon:(@Composable ()->Unit)?={
        if(!title.contains("WishList")){
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, tint = Color.Black, contentDescription = null)
            }
        }else{
            null
        }
    }
    TopAppBar(
        actions ={
            androidx.compose.material3.IconButton(onClick = { showMenu = true }) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More options",
                    tint = Color.White
                )
            }
            DropdownMenu(
                modifier = Modifier.background(colorResource(R.color.white)),
                expanded = showMenu,
                onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(onClick = {
                    showMenu = false

                }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        androidx.compose.material.Icon(
                            painter = painterResource(R.drawable.cloud),
                            contentDescription = "Add member",
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        androidx.compose.material3.Text("Cloud Wish", color = Color.Black)
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
                        androidx.compose.material.Icon(
                            painter = painterResource(R.drawable.baseline_games_24),
                            contentDescription = "Account",
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        androidx.compose.material3.Text("Show Wishes", color = Color.Black)
                    }
                }
                DropdownMenuItem(onClick = {
                    showMenu = false
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate(Screen.SignInScreen.route)

                }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        androidx.compose.material.Icon(
                            painter = painterResource(R.drawable.logout),
                            contentDescription = "Setting",
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        androidx.compose.material3.Text("Logout", color = Color.Black)
                    }
                }
            }
        } ,


        title = {
            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .heightIn(max = 24.dp) // Adjusting height to fit the text properly
            )
        }, navigationIcon = if(title=="Add Wish") naviagationIcon else null,
        elevation = 3.dp,
        backgroundColor = colorResource(R.color.top_bar), // Correct background color handling
        // Correct padding for the status bar
    )
}
