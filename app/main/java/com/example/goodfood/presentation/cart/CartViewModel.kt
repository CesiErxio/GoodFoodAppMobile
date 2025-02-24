package com.example.goodfood.presentation.cart

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodfood.domain.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    userDataRepository: UserDataRepository
) : ViewModel() {

    private val _cartState = mutableStateOf(
        CartState()
    )

    val cartState: State<CartState> = _cartState

    init {
        viewModelScope.launch {
            userDataRepository.getCartItems().collect{
                Log.e("getCartItems", it.toMutableList().toString())
                _cartState.value = cartState.value.copy(
                    list = it.toMutableList()
                )
            }
        }
    }
}