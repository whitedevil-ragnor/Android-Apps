package com.meow.wishlist.screens

//noinspection UsingMaterialAndMaterial3Libraries

//noinspection UsingMaterialAndMaterial3Libraries

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.OutlinedTextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextFieldDefaults
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.meow.wishlist.R
import com.meow.wishlist.TopNavBar
import com.meow.wishlist.ViewModels.WishViewModel
import com.meow.wishlist.room_setup.Wish
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditScreen(viewModel: WishViewModel, navController: NavController, id:Long){
    val scope= rememberCoroutineScope()
    val scaffoldState= rememberScaffoldState()
    val snackMsg = remember { mutableStateOf("") }
    if (id!=0L){
        val wish=viewModel.getWish(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishTitle=wish.value.title
        viewModel.wishDescription=wish.value.description
    }else{
        viewModel.wishTitle=""
        viewModel.wishDescription=""
    }
    Scaffold(modifier = Modifier.padding(top = 50.dp), scaffoldState = scaffoldState, topBar = { TopNavBar(title = if(id!=0L){ stringResource(
        R.string.update_screen
    )}else stringResource(R.string.add_screen), onBackClick = { navController.navigateUp() },navController) }
        ) {
        Column (modifier = Modifier.padding(it).wrapContentSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.height(8.dp))
            WishTextField("Title",viewModel.wishTitle, onValueChange = { viewModel.onTitleChange(it) })
            WishTextField("Description",viewModel.wishDescription,onValueChange = { viewModel.onDescriptionChange(it) })
            Spacer(modifier = Modifier.height(8.dp))
            Button( colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black, contentColor = Color.White
            ),
                shape = RoundedCornerShape(10.dp), elevation = ButtonDefaults.elevation(3.dp), onClick = {
                if (viewModel.wishTitle.isNotEmpty() &&viewModel.wishDescription.isNotEmpty()){
                    if (id!=0L){
                        viewModel.wishUpdate(
                            Wish(id=id, title = viewModel.wishTitle.trim(), description = viewModel.wishDescription.trim())
                        )
                        scope.launch {
                            navController.navigateUp()
                        }
                    }else{
                        //add msg
                        viewModel.wishInsert(
                            Wish(
                                title = viewModel.wishTitle.trim(),
                                description = viewModel.wishDescription.trim()
                            )
                        )
                        scope.launch {
                            navController.navigateUp()
                        }
                    }
                }else{
                    snackMsg.value="Enter the value"
                }
            }) {
                Text(text =if(id!=0L){ stringResource(R.string.update_screen)}else stringResource(R.string.add_screen), modifier = Modifier.padding(6.dp))
            }
        }
    }
}

@Composable
fun WishTextField(label:String,value:String,onValueChange:(String)->Unit){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },  // Wrapping Text inside a lambda
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            cursorColor = Color.Black
        )
    )
}