package com.example.goodfood.presentation.cart.components

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodfood.data.repository.UserDataRepositoryImpl
import com.example.goodfood.domain.repository.LoginRepository
import com.example.goodfood.domain.repository.UserDataRepository
import com.example.goodfood.domain.service.OrderRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillSectionViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val userDataRepositoryImpl: UserDataRepositoryImpl,
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    val userId: Flow<String> = repository.userid

    fun clearCartItem() {
        viewModelScope.launch {
            userDataRepository.clearCartItem()
        }
    }

    suspend fun addOrder(restaurantId: Int, userId: String, mealsIds: List<Int>, noOfItems: Int, totalPrice: Int) {
        val request = OrderRequest(
            "bankCard",
            "pending",
            restaurantId,
            totalPrice,
            userId.toInt(),
            mealsIds,
            noOfItems,
        )


        Log.e("ErrorOrder", request.toString())

        val order = userDataRepositoryImpl.addOrder(request)
        Log.e("ErrorOrder", order.toString())
    }
}