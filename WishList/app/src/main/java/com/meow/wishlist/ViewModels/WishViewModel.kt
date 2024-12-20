package com.meow.wishlist.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meow.wishlist.Navigation.Graph
import com.meow.wishlist.room_setup.Wish
import com.meow.wishlist.room_setup.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(private val wishRepository: WishRepository = Graph.wishRepository):ViewModel(){
    var wishTitle by mutableStateOf("")
    var wishDescription by mutableStateOf("")

    fun onTitleChange(str:String){
        wishTitle=str
    }
    fun onDescriptionChange(str:String){
        wishDescription=str
    }

    lateinit var getAllWishes : Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes=wishRepository.getAllWishes()
        }
    }
    fun wishInsert(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.wishInsert(wish)
        }
    }
    fun wishDelete(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.wishDelete(wish)
        }
    }
    fun wishUpdate(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.wishUpdate(wish)
        }
    }
    fun getWish(id:Long):Flow<Wish>{
       return wishRepository.getWish(id)
    }

}