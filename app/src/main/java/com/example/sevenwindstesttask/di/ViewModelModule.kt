package com.example.sevenwindstesttask.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sevenwindstesttask.presentation.login.LoginViewModel
import com.example.sevenwindstesttask.presentation.nearestCoffeeShops.NearestCoffeeShopsViewModel
import com.example.sevenwindstesttask.presentation.register.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory:ViewModelFactory):ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun loginViewModel(loginViewModel: LoginViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun registerViewModel(registerViewModel: RegisterViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NearestCoffeeShopsViewModel::class)
    abstract fun nearestCoffeeShopsViewModel(coffeeShopsViewModel:NearestCoffeeShopsViewModel):ViewModel
}