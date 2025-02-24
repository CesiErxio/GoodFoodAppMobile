package com.example.goodfood.presentation.history

import com.example.goodfood.domain.model.Restaurant

sealed class HistoryEvent {
    data class SelectRestaurant(val restaurant: Restaurant, val onClick: () -> Unit) :
        HistoryEvent()
}