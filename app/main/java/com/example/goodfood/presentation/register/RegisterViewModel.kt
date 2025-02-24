package com.example.goodfood.presentation.register

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodfood.domain.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Patterns
import com.example.goodfood.domain.constant.RoleEnum
import com.example.goodfood.domain.model.RegisteringUser
import com.example.goodfood.presentation.login.GoodFoodTextFieldState
import com.example.goodfood.presentation.login.UiEvent


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository
) : ViewModel() {

    private val _email = mutableStateOf(
        GoodFoodTextFieldState(
            hint = "Email"
        )
    )
    val email: State<GoodFoodTextFieldState> = _email

    private val _password = mutableStateOf(
        GoodFoodTextFieldState(
            hint = "Mot de passe"
        )
    )
    val password: State<GoodFoodTextFieldState> = _password

    private val _confirmPassword = mutableStateOf(
        GoodFoodTextFieldState(
            hint = "Confirmer le mot de passe"
        )
    )
    val confirmPassword: State<GoodFoodTextFieldState> = _confirmPassword

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

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredEmail -> {
                _email.value = email.value.copy(
                    text = event.value.trim()
                )
            }
            is RegisterEvent.EnteredPassword -> {
                _password.value = password.value.copy(
                    text = event.value.trim()
                )
            }
            is RegisterEvent.EnteredConfirmPassword -> {
                _confirmPassword.value = confirmPassword.value.copy(
                    text = event.value.trim()
                )
            }
            is RegisterEvent.EnteredFirstName -> {
                _firstName.value = firstName.value.copy(
                    text = event.value.trim()
                )
            }
            is RegisterEvent.EnteredLastName -> {
                _lastName.value = lastName.value.copy(
                    text = event.value.trim()
                )
            }
            is RegisterEvent.EnteredCity -> {
                _city.value = city.value.copy(
                    text = event.value.trim()
                )
            }
            is RegisterEvent.EnteredPhone -> {
                _phone.value = phone.value.copy(
                    text = event.value.trim()
                )
            }
            is RegisterEvent.PerformRegister -> {
                val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email.value.text).matches()
                val isValidPassword = password.value.text.isNotEmpty() && password.value.text == confirmPassword.value.text
                val isValidFirstName = firstName.value.text.isNotEmpty()
                val isValidLastName = lastName.value.text.isNotEmpty()
                val isValidCity = city.value.text.isNotEmpty()
                val isValidPhone = phone.value.text.isNotEmpty()

                if (isValidEmail && isValidPassword && isValidFirstName && isValidLastName && isValidCity && isValidPhone) {
                    viewModelScope.launch  {
                        val registeringUser = RegisteringUser(
                            firstName = firstName.value.text,
                            lastName = lastName.value.text,
                            city = city.value.text,
                            email = email.value.text,
                            phone = phone.value.text,
                            password = password.value.text,
                            passwordConfirm = confirmPassword.value.text,
                            userType = RoleEnum.USER.role,
                        )

                        val register = repository.registerUser(registeringUser)

                        if (register == 400) {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    message = "Ce compte existe déjà"
                                )
                            )
                        } else {
                            event.onClick()
                        }
                    }
                } else {
                    viewModelScope.launch{
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = "Veuillez remplir tous les champs correctement"
                            )
                        )
                    }
                }
            }

        }
    }
}