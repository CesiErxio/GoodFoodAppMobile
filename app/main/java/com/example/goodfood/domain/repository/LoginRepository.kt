package com.example.goodfood.domain.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun toggleLoginState(email: String, password: String): Int
    suspend fun logout()
    val loginState: Flow<Boolean>

    val userid: Flow<String>
    val firstName: Flow<String>
    val lastName: Flow<String>
    val email: Flow<String>
    val userType: Flow<String>
    val city: Flow<String>
}
