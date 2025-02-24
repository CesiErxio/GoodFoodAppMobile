package com.example.goodfood.domain.repository

import com.example.goodfood.data.repository.Results
import com.example.goodfood.domain.model.Advertisement
import com.example.goodfood.domain.model.FoodItem
import com.example.goodfood.domain.model.Restaurant

interface HomeRepository {
    suspend fun getRestaurants() : Results<List<Restaurant>>
    suspend fun getAds(): Results<List<Advertisement>>
    suspend fun getFoodItems():Results<List<FoodItem>>
    fun getRestaurantFromName(name: String): Restaurant?
}