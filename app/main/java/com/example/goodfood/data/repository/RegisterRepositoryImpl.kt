package com.example.goodfood.data.repository

import com.example.goodfood.domain.model.RegisteringUser
import com.example.goodfood.domain.repository.RegisterRepository
import com.example.goodfood.domain.service.GoodFoodApiService
import com.example.goodfood.domain.service.RegisterRequest

class RegisterRepositoryImpl() : RegisterRepository  {
    private val apiService = GoodFoodApiService()

    override suspend fun registerUser(registeringUser: RegisteringUser): Int {

        val request = RegisterRequest(
            registeringUser.firstName,
            registeringUser.lastName,
            registeringUser.city,
            registeringUser.email,
            registeringUser.phone,
            registeringUser.password,
            registeringUser.passwordConfirm,
            registeringUser.userType,
        )

        val response = apiService.api.register(request)

        return response.code()
    }

}
