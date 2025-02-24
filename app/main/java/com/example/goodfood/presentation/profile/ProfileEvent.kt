package com.example.goodfood.presentation.profile

import com.example.goodfood.presentation.login.LoginEvent


sealed class ProfileEvent {
    data class PerformLogout(val onClick: () -> Unit) : ProfileEvent()
}