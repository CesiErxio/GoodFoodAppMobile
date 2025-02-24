package com.example.goodfood.presentation.home

import com.example.goodfood.domain.model.Restaurant

sealed class HomeScreenEvent {
    data class SelectRestaurant(val restaurant: Restaurant, val onClick: () -> Unit) :
        HomeScreenEvent()
}