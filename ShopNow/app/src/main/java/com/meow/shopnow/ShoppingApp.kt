package com.meow.shopnow

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class Cart(
    var id:Int,
    var item:String,
    var qnty:Int,
    var modifyItem:Boolean=false
)
@Composable
fun ShoppingApp(){
    var sItems by remember { mutableStateOf(listOf<Cart>()) }
    var isBox by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemNos  by remember { mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){
        Button(onClick = { isBox=true }
            , modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Add Item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            items(sItems){
                item ->
                if (item.modifyItem){
                    editList(item = item, onEditComplete ={
                        editedName,editedQuantity->
                        sItems=sItems.map { it.copy(modifyItem = false) }
                        val editedItem=sItems.find { it.id==item.id }
                        editedItem?.let {
                            it.item=editedName
                            it.qnty=editedQuantity
                        }
                    } )
                }else{
                    ListItems(item = item,
                        onEditClick = {
                        //finding which item we  are editing ans changing "isEditing boolean " tio true
                        sItems=sItems.map { it.copy(modifyItem = it.id==item.id) }
                    }, onDeleteClick = {
                      sItems=sItems-item
                    })
                }
            }
        }
    }
    if (isBox){
        AlertDialog(onDismissRequest = { isBox=false },
            confirmButton =
            {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                Button(onClick = {
                   if (itemName.isNotBlank()){
                       var newItem=Cart(
                           id = sItems.size+1,
                           item=itemName,
                           qnty = itemNos.toInt()
                       )
                       sItems+=newItem
                       isBox=false
                       itemName=""
                       itemNos=""
                   }
                }) {
                    Text(text = "Add")
                }
                Button(onClick = {
                    isBox=false
                    itemName=""
                    itemNos=""}) {
                    Text(text = "Cancel")
                }
            }
            }//confirm
                    ,
            title = {
                    Text(text = "Add to Cart")
            },
            text = {
                Column {
                    OutlinedTextField(
                        value =itemName,
                        onValueChange ={
                        itemName=it },
                        singleLine = true,
                        placeholder = {
                            Text(text = "Item")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                        )
                    OutlinedTextField(value =itemNos,
                        onValueChange ={
                            itemNos=it},
                        singleLine = true,
                        placeholder = {
                             Text(text = "Quantity")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                }
            }
        )
    }
}
@Composable
fun ListItems(
    item:Cart,
    onEditClick:()->Unit,
    onDeleteClick:()->Unit,
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .border(
            border = BorderStroke(2.dp, Color.White),
            shape = RoundedCornerShape(20)
        ), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = item.item, modifier = Modifier.padding(8.dp))
        Text(text = "Qnt:${item.qnty}", modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.padding(8.dp)) {
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit , contentDescription =null )
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription =null )
            }
        }
    }
}
@Composable
fun editList(item: Cart,onEditComplete:(String,Int)->Unit) {
    var editName by remember { mutableStateOf(item.item) }
    var editQauantity by remember { mutableStateOf(item.qnty.toString()) }
    var isEdit by remember { mutableStateOf(item.modifyItem) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            BasicTextField(
                value =editName,
                onValueChange = {editName=it},
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp))
            BasicTextField(
                value =editQauantity,
                onValueChange = {editQauantity=it},
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp))
        }
        Button(onClick = { isEdit=false
        onEditComplete(editName,editQauantity.toIntOrNull()?:1)
        }) {
            Text(text = "Save")
        }
    }
}