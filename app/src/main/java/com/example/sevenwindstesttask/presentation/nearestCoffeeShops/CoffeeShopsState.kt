package com.example.sevenwindstesttask.presentation.nearestCoffeeShops

import com.example.sevenwindstesttask.data.responses.coffeeShops.CoffeeShop

sealed interface CoffeeShopsState {

    data class Success(val coffeeShops:List<CoffeeShop>):CoffeeShopsState

    object Error:CoffeeShopsState
    object Loading:CoffeeShopsState

}