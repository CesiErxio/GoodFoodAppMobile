package com.example.goodfood.presentation.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.goodfood.domain.repository.LoginRepository
import com.example.goodfood.presentation.util.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    repository: LoginRepository
) : ViewModel() {

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _startDestination: MutableState<String> = mutableStateOf(Screen.LoginScreen.route)
    val startDestination: State<String> = _startDestination

    init {

        val loginState = runBlocking {
            repository.loginState.first()
        }

        if (loginState) {
            _startDestination.value = Screen.Home.route

        } else {
            _startDestination.value = Screen.LoginScreen.route

        }


        _isLoading.value = false


    }
}