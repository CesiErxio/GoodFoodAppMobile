package com.example.goodfood.presentation.cart

import com.example.goodfood.domain.model.CartItem
import com.example.goodfood.domain.model.MenuItem
import com.example.goodfood.domain.model.Restaurant

data class CartState(
    val list: MutableList<CartItem> = mutableListOf()
)


