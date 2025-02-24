package com.example.goodfood.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodfood.data.repository.Results
import com.example.goodfood.domain.repository.HomeRepository
import com.example.goodfood.domain.repository.LoginRepository
import com.example.goodfood.domain.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    private val Loginrepository: LoginRepository,
    private val userDataRepository: UserDataRepository,

) : ViewModel() {

    val firstName: Flow<String> = Loginrepository.firstName
    val lastName: Flow<String> = Loginrepository.lastName
    val email: Flow<String> = Loginrepository.email
    val userType: Flow<String> = Loginrepository.userType

    private val _homeScreenState = mutableStateOf(
        HomeScreenState()
    )
    val homeScreenState: State<HomeScreenState> = _homeScreenState

    init {
        viewModelScope.launch {
            when (val result = repository.getAds()) {
                is Results.Success -> _homeScreenState.value = homeScreenState.value.copy(
                    adsList = result.data
                )
                is Results.Error -> {
                }
            }

            when (val result = repository.getFoodItems()) {
                is Results.Success -> _homeScreenState.value = homeScreenState.value.copy(
                    foodList = result.data
                )
                is Results.Error -> {

                }
            }

            when (val result = repository.getRestaurants()) {
                is Results.Success -> _homeScreenState.value = homeScreenState.value.copy(
                    restaurantList = result.data,
                )
                is Results.Error -> {

                }
            }

            userDataRepository.getLikedRestaurants().collect {
                _homeScreenState.value = homeScreenState.value.copy(
                    likedRestaurantList = it
                )
            }
        }
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.SelectRestaurant -> {
                viewModelScope.launch {
                    userDataRepository.setRestaurant(event.restaurant)
                    event.onClick()
                }
            }
        }
    }


}