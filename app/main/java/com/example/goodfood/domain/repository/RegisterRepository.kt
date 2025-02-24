package com.example.goodfood.domain.repository

import com.example.goodfood.domain.model.RegisteringUser


interface RegisterRepository {
    suspend fun registerUser(registeringUser: RegisteringUser): Int
}
