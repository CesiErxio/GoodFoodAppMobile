package com.example.goodfood.presentation.cart.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodfood.domain.model.MenuItem
import com.example.goodfood.domain.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartItemCardViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    fun addCartItem(menuItem: MenuItem, quantity: Int) {
        viewModelScope.launch {
            userDataRepository.addCartItem(menuItem, quantity)
        }
    }

    fun removeCartItem(menuItem: MenuItem, quantity: Int) {
        viewModelScope.launch {
            userDataRepository.removeCartItem(menuItem, quantity)
        }
    }

}