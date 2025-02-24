package com.example.goodfood.presentation.profile.profileInformations

import com.example.goodfood.presentation.login.LoginEvent
import com.example.goodfood.presentation.register.RegisterEvent


sealed class ProfileInformationsEvent {

    data class EnteredEmail(val value: String) : ProfileInformationsEvent()
    data class EnteredFirstName(val value: String) : ProfileInformationsEvent()
    data class EnteredLastName(val value: String) : ProfileInformationsEvent()
    data class EnteredCity(val value: String) : ProfileInformationsEvent()
    data class EnteredPhone(val value: String) : ProfileInformationsEvent()
    data class PerformHome(val onClick: () -> Unit) : ProfileInformationsEvent()
}