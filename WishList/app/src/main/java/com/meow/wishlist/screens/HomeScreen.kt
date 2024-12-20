package com.meow.wishlist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FabPosition
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.FloatingActionButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.meow.wishlist.Navigation.Screen
import com.meow.wishlist.TopNavBar
import com.meow.wishlist.ViewModels.WishViewModel
import com.meow.wishlist.room_setup.Wish


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(navController: NavController,viewModel: WishViewModel){
    Scaffold(
        backgroundColor = Color.White,
        modifier = Modifier.padding(top = 50.dp),
        topBar ={ TopNavBar("WishList", navController = navController) },
        floatingActionButton = {
            FloatingActionButton(
                modifier =Modifier.padding(30.dp), onClick = {
                    navController.navigate(Screen.WishScreen.route+"/0L")
                    }, contentColor = Color.White, backgroundColor = Color.Black
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
       floatingActionButtonPosition = FabPosition.End,
    ) {
        val wishList=viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn (modifier = Modifier.fillMaxSize().padding(it)){
            items(wishList.value, key ={ wish -> wish.id}){
                wish->
                val dismissState= rememberDismissState(
                    confirmStateChange = {
                        if(it==DismissValue.DismissedToEnd || it==DismissValue.DismissedToStart){
                            viewModel.wishDelete(wish)
                        }
                        true
                    }
                )
                SwipeToDismiss(
                  state = dismissState, background = {
                     val alignment=Alignment.CenterEnd
                        Box(Modifier.fillMaxSize().background(Color.White).padding(horizontal = 20.dp), contentAlignment = alignment){
                            Icon(Icons.Default.Delete, contentDescription = "delete", tint = Color.Black)
                        }
                    }, dismissThresholds = {FractionalThreshold(0.40f)},
                    directions = setOf(DismissDirection.EndToStart),
                    dismissContent = {
                        WishBox(wish) {
                            val id = wish.id
                            navController.navigate(Screen.WishScreen.route + "/$id")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun WishBox(wish: Wish, onClick:()->Unit){
     Card(modifier = Modifier.fillMaxWidth().padding(top = 8.dp, start = 8.dp,end=8.dp).clickable { onClick() }, elevation = 16.dp, backgroundColor = Color.White, contentColor = Color.Black){
         Column {
             Text(text = wish.title, fontWeight = FontWeight.ExtraBold, color = Color.Black, modifier = Modifier.padding(4.dp))
             Text(text = wish.description, color = Color.Black ,modifier = Modifier.padding(2.dp))
         }
     }
}