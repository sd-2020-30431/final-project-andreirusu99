package com.shopping.baseproject.core.main.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopping.baseproject.data.api.ShoppingProvider
import com.shopping.baseproject.data.models.Store
import com.shopping.baseproject.shared.event.BaseEvent
import com.shopping.baseproject.shared.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class MapViewModel(private val shoppingProvider: ShoppingProvider) : ViewModel() {

    private val _cmd = SingleLiveEvent<Command>()
    val cmd: LiveData<Command>
        get() = _cmd

    private val _stores = MutableLiveData<List<Store>>()
    val stores: LiveData<List<Store>>
        get() = _stores

    init {
        getStoresFromAPI()
    }

    private fun getStoresFromAPI() {
        viewModelScope.launch {
            val stores = shoppingProvider.getStores()
            _stores.value = stores.successOr(listOf())
        }
    }

    sealed class Command : BaseEvent
}
