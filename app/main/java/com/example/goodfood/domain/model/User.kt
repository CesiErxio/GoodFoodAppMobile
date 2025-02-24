package com.example.goodfood.domain.model

import com.example.goodfood.domain.constant.RoleEnum

open class User(
    val userId: String,
    open val firstName: String,
    open val lastName: String,
    open val city: String,
    open val email: String,
    open val phone: String,
    open val userType: String = RoleEnum.USER.toString()
)

data class RegisteringUser(
    override val firstName: String,
    override val lastName: String,
    override val city: String,
    override val email: String,
    override val phone: String,
    val password: String,
    val passwordConfirm: String,
    override val userType: String = RoleEnum.USER.toString()
) : User("", firstName, lastName, city, email, phone, userType)
