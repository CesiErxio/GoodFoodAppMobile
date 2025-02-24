package com.example.goodfood.domain.model

data class OrderItem(
    val restaurantId: Int,
    val userId: Int,
    val menuItemId: Int,
    var noOfItems: Int,
    val totalPrice: Int,
)
