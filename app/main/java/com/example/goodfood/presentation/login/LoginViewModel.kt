package com.example.goodfood.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodfood.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Patterns


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
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

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                _email.value = email.value.copy(
                    text = event.value.trim()
                )

            }
            is LoginEvent.EnteredPassword -> {
                _password.value = password.value.copy(
                    text = event.value.trim()
                )
            }
            is LoginEvent.PerformLogin -> {
                if (Patterns.EMAIL_ADDRESS.matcher(email.value.text).matches() && password.value.text.isNotEmpty()){
                    viewModelScope.launch  {
                        val login = repository.toggleLoginState(email.value.text, password.value.text)

                        if (login == 404) {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    message = "Ce compte n'existe pas"
                                )
                            )
                        } else if (login == 401) {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    message = "Mauvais mot de passe"
                                )
                            )
                        }
                        else {
                            event.onClick()
                        }
                    }
                }
                else {
                    viewModelScope.launch{
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = "Entrer un email et mot de passe correct"
                            )
                        )
                    }
                }
            }
        }
    }

}