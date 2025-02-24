package com.example.goodfood.data.repository

import android.util.Log
import com.example.goodfood.data.data_source.cartList
//import com.example.goodfood.data.data_source.favouriteList
import com.example.goodfood.domain.model.CartItem
import com.example.goodfood.domain.model.MenuItem
import com.example.goodfood.domain.model.Restaurant
import com.example.goodfood.domain.repository.UserDataRepository
import com.example.goodfood.domain.service.GoodFoodApiService
import com.example.goodfood.domain.service.OrderRequest
import com.example.goodfood.domain.service.RegisterRequest
import com.example.goodfood.domain.service.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class UserDataRepositoryImpl @Inject constructor() : UserDataRepository {
    private val apiService = GoodFoodApiService()

    private val menuList = mutableSetOf<CartItem>()
    private val cartListItems = mutableSetOf<CartItem>()
    private var currentRestaurant: Restaurant? = null


    override suspend fun getUserById(id: Int): UserResponse? {
        Log.e("RuntimeException", id.toString())
        val response = apiService.api.getUserById(id)
        if (response.isSuccessful) {
            Log.e("RuntimeException", response.body().toString())
            return response.body();
        } else {
            throw RuntimeException("API request failed with status: ${response.code()}")
        }
    }


    override suspend fun setRestaurant(restaurant: Restaurant) {
        currentRestaurant = restaurant
        restaurant.menu.forEach {
            menuList.add(CartItem(it, 0))
        }
    }

    override suspend fun getSavedRestaurant(): Flow<Restaurant> = flow {
        if (currentRestaurant != null) {
            emit(currentRestaurant!!)
        }
    }

    override suspend fun getMenuItems(): Flow<List<CartItem>> = flow {
        emit(menuList.toMutableList())
    }

    override suspend fun getLikedRestaurants(): Flow<List<Restaurant>> = flow {
        emit(emptyList())
    }

    override suspend fun isRestaurantLiked(restaurant: Restaurant): Flow<Boolean> = flow {
        emit(false)
    }

    override suspend fun getCartItems(): Flow<List<CartItem>> = flow {
//        Log.e("ErrorCart", cartListItems.toString())

        emit(cartListItems.toMutableList())
    }

    override suspend fun addCartItem(menuItem: MenuItem, noOfItems: Int) {
        Log.e("ErrorCart", menuItem.toString())
        val existingCartItem = cartListItems.find { it.menuItem.dish == menuItem.dish }
        if (existingCartItem != null) {
            existingCartItem.noOfItems += noOfItems
        } else {
            Log.e("ErrorCart", cartListItems.toString())
            cartListItems.add(CartItem(menuItem, noOfItems))
        }

    }


    override suspend fun removeCartItem(menuItem: MenuItem, noOfItem: Int) {
        Log.e("ErrorCart", menuItem.toString())
        val existingCartItem = cartListItems.find { it.menuItem.dish == menuItem.dish }
        if (existingCartItem != null) {
            if (existingCartItem.noOfItems > noOfItem) {
                existingCartItem.noOfItems -= noOfItem
            } else {
                Log.e("ErrorCart", cartListItems.toString())
                cartListItems.remove(existingCartItem)
            }
        }

    }

    override suspend fun clearCartItem() {
        cartListItems.clear()
    }


    override suspend fun addOrder(request: OrderRequest): Int {
        val response = apiService.api.addOrder(request)
        Log.e("ErrorOrder", response.message())

        return response.code();
    }
}