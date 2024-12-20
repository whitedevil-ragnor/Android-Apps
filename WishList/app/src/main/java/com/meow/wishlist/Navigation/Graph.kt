package com.meow.wishlist.Navigation

import android.content.Context
import androidx.room.Room
import com.meow.wishlist.room_setup.WishDataBase
import com.meow.wishlist.room_setup.WishRepository

object Graph {
    lateinit var dataBase: WishDataBase
    val wishRepository by lazy {
        WishRepository(dataBase.wishDao())
    }
    fun provide(context: Context){
        dataBase = Room.databaseBuilder(context, WishDataBase::class.java,name="WishDatabase.db").build()
    }
}