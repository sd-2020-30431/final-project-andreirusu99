package com.shopping.baseproject.core.main.grocerylist

import androidx.lifecycle.*
import com.shopping.baseproject.data.api.*
import com.shopping.baseproject.data.models.AisleModel
import com.shopping.baseproject.data.models.StoreCellModel
import com.shopping.baseproject.shared.event.BaseEvent
import com.shopping.baseproject.shared.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class GroceryListViewModel(private val shoppingProvider: ShoppingProvider) : ViewModel() {

    private val _cmd = SingleLiveEvent<Command>()
    val cmd: LiveData<Command>
        get() = _cmd

    private val _groceryList = MutableLiveData<List<AisleModel>>()
    val groceryList: LiveData<List<AisleModel>>
        get() = _groceryList

    val cart = Transformations.map(_groceryList) {
        it.filter { a -> a.isInCart }
    }

    var cartCounter = Transformations.map(_groceryList) {
        it.count { a -> a.isInCart }
    }

    private var currentStoreCellSelected: StoreCellModel = StoreCellModel(
        id = 171,
        col = 3, row = 18, type = -3, store_id = 1
    )

    val _mapCallSucceeded = MutableLiveData<Boolean>().apply { value = false }
    val mapCallSucceeded: MutableLiveData<Boolean>
        get() = _mapCallSucceeded

    fun setCurrentStoreCell(storeCellModel: StoreCellModel) {
        currentStoreCellSelected = storeCellModel
    }

    private val _path = MutableLiveData<List<StoreCell>>()
    val path: LiveData<List<StoreCell>>
        get() = _path

    private val _storeMap = MutableLiveData<List<StoreCell>>()
    private val storeMap: LiveData<List<StoreCell>>
        get() = _storeMap

    val storeRoute = MediatorLiveData<List<StoreCellModel>>()

    init {
        getAislesFromAPI()
        storeRoute.addSource(storeMap) {
            storeRoute.value = combineLatestData(storeMap, path)
        }
        storeRoute.addSource(path) {
            storeRoute.value = combineLatestData(storeMap, path)
        }
    }

    private fun combineLatestData(
        storeMap: LiveData<List<StoreCell>>,
        path: LiveData<List<StoreCell>>
    ): List<StoreCellModel> {

        val storeMapValue = storeMap.value
        val pathValue = path.value

        // Don't send a success until we have both results
        if (storeMapValue == null || pathValue == null) {
            return listOf()
        }

        return storeMapValue.map {
            StoreCellModel(
                id = it.id,
                row = it.row,
                col = it.col,
                type = it.type,
                store_id = it.store_id,
                isInPath = isInPath(it)
            )
        }
    }

    private fun getAislesFromAPI() {
        viewModelScope.launch {
            val aisles = shoppingProvider.getAllAisles()
            _groceryList.value =
                aisles.successOr(listOf()).map {
                    AisleModel(
                        id = it.id,
                        name = it.name,
                        icon = it.icon,
                        barcode = it.barcode
                    )
                }
        }
    }

    fun addOrRemoveFromCart(aisleModel: AisleModel) {
        _groceryList.value = _groceryList.value?.map {
            if (it.id == aisleModel.id)
                it.copy(isInCart = !aisleModel.isInCart)
            else
                it
        }
    }

    fun removeAisleWithBarcodeFromCart(barcode: String): String {
        val scannedAisle = _groceryList.value?.find { it.barcode == barcode && it.isInCart }
        scannedAisle?.let {
            addOrRemoveFromCart(scannedAisle)
        }
        return if (scannedAisle != null)
            "You picked up product from " + scannedAisle.name else "This item is not in your cart."
    }

    fun getShopMapFromAPI(id: Int) {
        viewModelScope.launch {
            val storeMap = shoppingProvider.getShopMap(id)
            _storeMap.value = storeMap.successOr(listOf())
        }
    }

    fun getPathFromAPI(id: Int) {
        val start = currentStoreCellSelected
        viewModelScope.launch {
            cart.value?.let { cart ->
                val path = shoppingProvider.getPath(
                    id,
                    start.id,
                    cart.map {
                        Aisle(
                            icon = it.icon,
                            id = it.id,
                            name = it.name,
                            barcode = it.barcode
                        )
                    }
                )
                _path.value = path.successOr(listOf())
                if (path.succeeded)
                    _mapCallSucceeded.postValue(true)
            }
        }
    }

    private fun isInPath(storeCell: StoreCell): Boolean {
        _path.value?.let {
            return it.contains(storeCell)
        }
        return false
    }

    fun getSelectedAisle(storeCellModel: StoreCellModel) =
        cart.value?.find { it.id == storeCellModel.type }

    sealed class Command : BaseEvent
}
