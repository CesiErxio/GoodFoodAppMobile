package com.example.goodfood.presentation.details

import com.example.goodfood.domain.model.CartItem
import com.example.goodfood.domain.model.Restaurant

data class DetailScreenState(
    val restaurant: Restaurant? = null,
    val recommendedExpandedState: Boolean = true,
    val nonVegExpandedState: Boolean = true,
    val vegExpandedState: Boolean = true,
    val menuList: List<CartItem> = emptyList(),
    val recommendedList: List<CartItem> = emptyList(),
    val isLiked: Boolean = false,



)
