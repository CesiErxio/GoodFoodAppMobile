package com.example.goodfood.presentation.home

import com.example.goodfood.domain.model.Advertisement
import com.example.goodfood.domain.model.FoodItem
import com.example.goodfood.domain.model.Restaurant

data class HomeScreenState(
    val adsList: List<Advertisement> = emptyList(),
    val foodList: List<FoodItem> = emptyList(),
    val likedRestaurantList : List<Restaurant> = emptyList(),
    val restaurantList : List<Restaurant> = emptyList(),
)
