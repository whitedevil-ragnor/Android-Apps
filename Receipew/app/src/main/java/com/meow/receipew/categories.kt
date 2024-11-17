package com.meow.receipew

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Define the data class for individual categories
@Parcelize
data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
) : Parcelable

// Define the response class that matches the API response structure
data class CategoriesResponse(
    val categories: List<Category> // This should match the JSON key in the response
)
