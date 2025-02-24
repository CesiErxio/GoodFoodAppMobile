package com.example.goodfood.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodfood.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    val firstName: Flow<String> = repository.firstName
    val lastName: Flow<String> = repository.lastName
    val email: Flow<String> = repository.email
    val userType: Flow<String> = repository.userType

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.PerformLogout -> {
                viewModelScope.launch()  {
                    repository.logout()
                    event.onClick()
                }
            }
        }
    }
}
