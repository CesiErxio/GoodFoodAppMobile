package com.example.goodfood.presentation.profile.profileInformations

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodfood.domain.constant.RoleEnum
import com.example.goodfood.domain.model.RegisteringUser
import com.example.goodfood.domain.model.User
import com.example.goodfood.domain.repository.LoginRepository
import com.example.goodfood.domain.repository.RegisterRepository
import com.example.goodfood.domain.repository.UserDataRepository
import com.example.goodfood.presentation.login.GoodFoodTextFieldState
import com.example.goodfood.presentation.login.UiEvent
import com.example.goodfood.presentation.register.RegisterEvent
import kotlinx.coroutines.flow.Flow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileInformationsViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val userDataRepository: UserDataRepository,
    private val registerRepository: RegisterRepository
) : ViewModel() {
    val userId: Flow<String> = loginRepository.userid


    init {
        viewModelScope.launch {
            userId.collect { id ->
                val user = userDataRepository.getUserById(id.toInt())
                Log.e("LogError", "User: $user")
                if (user != null) {
                    _email.value = _email.value.copy(text = user.email)
                }
                if (user != null) {
                    _firstName.value = _firstName.value.copy(text = user.firstName)
                }
                if (user != null) {
                    _lastName.value = _lastName.value.copy(text = user.lastName)
                }
                if (user != null) {
                    _city.value = _city.value.copy(text = user.city)
                }
                if (user != null) {
                    _phone.value = _phone.value.copy(text = user.phone)
                }
            }
        }
    }

    private val _email = mutableStateOf(
        GoodFoodTextFieldState(
            hint = "Email"
        )
    )
    val email: State<GoodFoodTextFieldState> = _email

    private val _firstName = mutableStateOf(
        GoodFoodTextFieldState(
            hint = "Prénom"
        )
    )
    val firstName: State<GoodFoodTextFieldState> = _firstName

    private val _lastName = mutableStateOf(
        GoodFoodTextFieldState(
            hint = "Nom"
        )
    )
    val lastName: State<GoodFoodTextFieldState> = _lastName

    private val _city = mutableStateOf(
        GoodFoodTextFieldState(
            hint = "Ville"
        )
    )
    val city: State<GoodFoodTextFieldState> = _city

    private val _phone = mutableStateOf(
        GoodFoodTextFieldState(
            hint = "Numéro de téléphone"
        )
    )
    val phone: State<GoodFoodTextFieldState> = _phone

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: ProfileInformationsEvent) {
        when (event) {
            is ProfileInformationsEvent.EnteredEmail -> {
                _email.value = email.value.copy(
                    text = event.value.trim()
                )
            }
            is ProfileInformationsEvent.EnteredFirstName -> {
                _firstName.value = firstName.value.copy(
                    text = event.value.trim()
                )
            }
            is ProfileInformationsEvent.EnteredLastName -> {
                _lastName.value = lastName.value.copy(
                    text = event.value.trim()
                )
            }
            is ProfileInformationsEvent.EnteredCity -> {
                _city.value = city.value.copy(
                    text = event.value.trim()
                )
            }
            is ProfileInformationsEvent.EnteredPhone -> {
                _phone.value = phone.value.copy(
                    text = event.value.trim()
                )
            }
            is ProfileInformationsEvent.PerformHome -> {
                val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email.value.text).matches()
                val isValidFirstName = firstName.value.text.isNotEmpty()
                val isValidLastName = lastName.value.text.isNotEmpty()
                val isValidCity = city.value.text.isNotEmpty()
                val isValidPhone = phone.value.text.isNotEmpty()

                if (isValidEmail && isValidFirstName && isValidLastName && isValidCity && isValidPhone) {
                    viewModelScope.launch  {
                        val modifyUser = User(
                            userId = userId.toString(),
                            firstName = firstName.value.text,
                            lastName = lastName.value.text,
                            city = city.value.text,
                            email = email.value.text,
                            phone = phone.value.text,
                            userType = RoleEnum.USER.role,
                        )

//                        val modify = registerRepository.registerUser(modifyUser)
                        val modify = 400
                        if (modify == 400) {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    message = "Echec modification"
                                )
                            )
                        } else {
                            event.onClick()
                        }
                    }
                }
            }

        }
    }
}