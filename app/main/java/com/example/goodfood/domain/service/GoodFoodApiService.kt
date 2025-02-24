package com.example.goodfood.domain.service

import com.example.goodfood.domain.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val jwt: String, val user: User)
data class RegisterRequest(val firstName: String, val lastName: String, val city: String, val email: String, val phone: String, val password: String, val passwordConfirm: String, val userType: String)
data class RegisterResponse(val message: String)

data class OrderRequest(
    val paymentMethod: String,
    val status: String,
    val restaurantId: Int,
    val totalPrice: Int,
    val userId: Int,
    val mealsIds: List<Int>,
    val noOfItems: Int,

    )
data class OrderResponse(val message: String)

data class UserResponse(
    val userId: Int,
    val firstName: String,
    val lastName: String,
    val city: String,
    val email: String,
    val phone: String,
    val userType: String,
    val interests: String
)

data class EventResponse(
    val eventId: Int,
    val type: String,
    val name: String,
    val description: String,
    val date: String,
    val guestCount: Int,
    val capacity: Int,
)

data class RestaurantResponse(
    val restaurantId: Int,
    val name: String,
    val type: String,
    val isActive: Boolean,
    val baseurl: String,
    val averageRating: Double,
    val numberOfRatings: Int,
    val timeToDeliverInMinutes: Int,
    val address: Address,
    val meals: List<Meal>,
    val activeDeliveryTypes: List<activeDeliveryTypes>
)

data class Address(
    val addressId: Int,
    val number: String,
    val street: String,
    val city: String,
    val zipCode: String,
    val country: String,
    val locationX: String,
    val locationY: String
)

data class Meal(
    val mealId: Int,
    val name: String,
    val type: String,
    val price: Double,
    val baseurl: String
)

data class activeDeliveryTypes(
    val deliveryTypeId: Int,
    val name: String,
    val priceByKm: Int,
    val basePrice: Int
)

interface GoodFoodApi {
    @POST("user/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("user/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @GET("user")
    suspend fun getUserById(@Query("userId") userId: Int): Response<UserResponse>

    @GET("event/all")
    suspend fun getAllEvents(): List<EventResponse>

    @GET("restaurant/all")
    suspend fun getAllRestaurants(): List<RestaurantResponse>

    @POST("order")
    suspend fun addOrder(@Body request: OrderRequest): Response<OrderResponse>
}

class GoodFoodApiService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://api.goodfood.pistonpulse.fr/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: GoodFoodApi = retrofit.create(GoodFoodApi::class.java)
}