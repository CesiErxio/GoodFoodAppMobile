package com.example.goodfood.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.ui.graphics.Color
import com.example.goodfood.R
import com.example.goodfood.domain.model.Advertisement
import com.example.goodfood.domain.model.FoodItem
import com.example.goodfood.domain.model.MenuItem
import com.example.goodfood.domain.model.Restaurant
import com.example.goodfood.domain.repository.HomeRepository
import com.example.goodfood.domain.service.GoodFoodApiService
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HomeRepositoryImpl() : HomeRepository {

    private val apiService = GoodFoodApiService()
    private var restaurantList: List<Restaurant> = listOf()

    @SuppressLint("NewApi")
    suspend fun fetchAndFormatEvents(): List<Advertisement> {
        val events = apiService.api.getAllEvents()

        return events.map { event ->
            val dateTime = LocalDateTime.parse(event.date, DateTimeFormatter.ISO_DATE_TIME)
            val formattedDate = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            val formattedSubTitle = event.description + "\n\nDate : " + formattedDate

            val randomColor = Color(
                red = (200..255).random(),
                green = (200..255).random(),
                blue = (200..255).random()
            )

            Advertisement(
                title = event.name,
                subTitle = formattedSubTitle,
                color = randomColor,
            )
        }
    }

    @SuppressLint("NewApi")
    suspend fun fetchAndFormatRestaurants(): List<Restaurant> {
        val restaurants = apiService.api.getAllRestaurants()

        val formattedRestaurants = restaurants.map { restaurant ->
            Restaurant(
                name = restaurant.name,
                rating = restaurant.averageRating,
                noOfRatings = restaurant.numberOfRatings,
                timeInMillis = restaurant.timeToDeliverInMinutes,
                variety = restaurant.type,
                place = restaurant.address.city,
                averagePrice = restaurant.meals.map { it.price }.average(),
                image = R.drawable.pizza,
                menu = restaurant.meals.map { meal ->
                    MenuItem(
                        id = meal.mealId,
                        dish = meal.name,
                        price = meal.price,
                        rating = 0.0,
                        noOfRatings = 0,
                        isVegetarian = false,
                    )
                }
            )
        }

        restaurantList = formattedRestaurants
        return formattedRestaurants
    }

    override suspend fun getRestaurants(): Results<List<Restaurant>> {
        return Results.Success(fetchAndFormatRestaurants())
    }

    override suspend fun getAds(): Results<List<Advertisement>> {
        return Results.Success(fetchAndFormatEvents())
    }

    override suspend fun getFoodItems(): Results<List<FoodItem>> {
        return Results.Success(emptyList())
    }

    override fun getRestaurantFromName(name: String): Restaurant? {
        return restaurantList.find { it.name == name }
    }
}