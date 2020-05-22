package com.shopping.baseproject.application

import com.shopping.baseproject.core.main.MainActivityViewModel
import com.shopping.baseproject.core.main.auth.login.LoginViewModel
import com.shopping.baseproject.core.main.auth.register.RegisterViewModel
import com.shopping.baseproject.core.main.doneShopping.DoneShoppingViewModel
import com.shopping.baseproject.core.main.grocerylist.GroceryListViewModel
import com.shopping.baseproject.core.main.map.MapViewModel
import com.shopping.baseproject.core.main.splash.SplashScreenViewModel
import com.shopping.baseproject.data.api.AuthAPI
import com.shopping.baseproject.data.api.AuthProvider
import com.shopping.baseproject.data.api.ShoppingAPI
import com.shopping.baseproject.data.api.ShoppingProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModules {
    private val viewModels = module {
        viewModel { LoginViewModel() }
        viewModel { RegisterViewModel() }
        viewModel { GroceryListViewModel(get()) }
        viewModel { MapViewModel(get()) }
    }

    private val remoteModule = module {
        single { ShoppingAPI.create() }
        single { ShoppingProvider() }
        single { AuthAPI.create() }
        single { AuthProvider() }
    }
    val modules = viewModels + remoteModule
}
