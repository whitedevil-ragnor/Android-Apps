package com.meow.wishlist

import android.app.Application
import com.meow.wishlist.Navigation.Graph

class WishListApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}