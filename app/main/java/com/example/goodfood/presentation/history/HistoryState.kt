package com.example.goodfood.presentation.history

import com.example.goodfood.domain.model.Restaurant

data class HistoryState(
    val likedRestaurantList: List<Restaurant> = emptyList(),
)
