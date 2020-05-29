package com.shopping.baseproject.core.main.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopping.baseproject.data.api.ShoppingProvider
import kotlinx.coroutines.launch

class SplashScreenViewModel(private val shoppingProvider: ShoppingProvider) : ViewModel() {

    fun start() {
        viewModelScope.launch {
            shoppingProvider.getAllAisles()
        }
    }
}
