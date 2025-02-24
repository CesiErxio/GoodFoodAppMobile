package com.example.goodfood.presentation.home.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goodfood.domain.model.MenuItem
import com.example.goodfood.domain.repository.LoginRepository
import com.example.goodfood.domain.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopSectionViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    val city: Flow<String> = repository.city

}