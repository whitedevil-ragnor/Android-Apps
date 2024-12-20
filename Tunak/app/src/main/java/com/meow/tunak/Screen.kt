package com.meow.tunak

import androidx.annotation.DrawableRes

sealed class Screen(var title:String, val route:String) {
    sealed class BottomScreen(val bTitle:String, val bRoute:String, @DrawableRes val icon: Int):Screen(bTitle,bRoute){
        data object Home:BottomScreen(
            bTitle = "Home", bRoute = "home", icon = (R.drawable.ic_home)
        )
        data object Library:BottomScreen(
            bTitle = "Library", bRoute = "library", icon = R.drawable.baseline_video_library_24
        )
        data object Browse:BottomScreen(
            bTitle = "Browse", bRoute = "browse", icon = R.drawable.baseline_language_24
        )
    }
    sealed class DrawerScreen(val dTitle:String, val dRoute:String, @DrawableRes val icon: Int):Screen(dTitle,dRoute){
        data object account:DrawerScreen(
            dTitle = "Account", dRoute = "account", icon = R.drawable.ic_account)
        data object subscription:DrawerScreen(
            dTitle = "Subscription", dRoute = "subscription", icon =R.drawable.ic_subscription
        )
        data object addAccount:DrawerScreen(
            dTitle = "Add Account", dRoute = "add_account", icon = R.drawable.ic_add_account
        )
    }
}
val screensInBottom=listOf(Screen.BottomScreen.Home,Screen.BottomScreen.Library,Screen.BottomScreen.Browse)
val screensInDrawer= listOf(Screen.DrawerScreen.account,Screen.DrawerScreen.subscription,Screen.DrawerScreen.addAccount)
