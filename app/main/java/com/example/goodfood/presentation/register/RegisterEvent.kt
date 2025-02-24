package com.example.goodfood.presentation.register

sealed class RegisterEvent {
    data class EnteredEmail(val value: String) : RegisterEvent()
    data class EnteredPassword(val value: String) : RegisterEvent()
    data class EnteredConfirmPassword(val value: String) : RegisterEvent()
    data class EnteredFirstName(val value: String) : RegisterEvent()
    data class EnteredLastName(val value: String) : RegisterEvent()
    data class EnteredCity(val value: String) : RegisterEvent()
    data class EnteredPhone(val value: String) : RegisterEvent()
    data class PerformRegister(val onClick: () -> Unit) : RegisterEvent()
}