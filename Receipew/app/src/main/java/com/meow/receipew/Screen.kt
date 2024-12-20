package com.meow.receipew

import kotlinx.parcelize.Parcelize


sealed class Screen(val route:String) {
    data object RecipeScreen:Screen("recipescreen")
    data object DetailScreen:Screen("detailscreen")
}