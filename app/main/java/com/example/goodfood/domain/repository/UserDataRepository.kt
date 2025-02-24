package com.example.goodfood.domain.repository

import com.example.goodfood.domain.model.CartItem
import com.example.goodfood.domain.model.MenuItem
import com.example.goodfood.domain.model.Restaurant
import com.example.goodfood.domain.service.OrderRequest
import com.example.goodfood.domain.service.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    suspend fun getUserById(id: Int): UserResponse?
    suspend fun setRestaurant(restaurant: Restaurant)
    suspend fun getSavedRestaurant(): Flow<Restaurant>
    suspend fun getMenuItems(): Flow<List<CartItem>>
    suspend fun getLikedRestaurants(): Flow<List<Restaurant>>
    suspend fun isRestaurantLiked(restaurant: Restaurant): Flow<Boolean>
    suspend fun getCartItems(): Flow<List<CartItem>>
    suspend fun addCartItem(menuItem: MenuItem, noOfItems: Int)
    suspend fun removeCartItem(menuItem: MenuItem, noOfItem: Int)
    suspend fun clearCartItem()
    suspend fun addOrder(request: OrderRequest): Int
}