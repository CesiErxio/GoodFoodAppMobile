package com.example.goodfood.presentation.util

sealed class Screen(val route: String) {
    object LoginScreen : Screen(route = "login_screen")
    object RegisterScreen : Screen(route = "register_screen")
    object Home : Screen(route = "home_screen")
    object History : Screen(route = "history")
    object Cart : Screen(route = "cart")
    object Profile : Screen(route = "profile")
    object ProfileInformations : Screen(route = "profile_informations")
    object RestaurantDetails:Screen(route = "restaurant_details")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
