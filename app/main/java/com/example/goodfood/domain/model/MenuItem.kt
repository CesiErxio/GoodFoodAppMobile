package com.example.goodfood.domain.model

data class MenuItem(
    val id: Int,
    val dish: String,
    val price: Double,
    val rating: Double,
    val noOfRatings: Int,
    val isVegetarian: Boolean
)
