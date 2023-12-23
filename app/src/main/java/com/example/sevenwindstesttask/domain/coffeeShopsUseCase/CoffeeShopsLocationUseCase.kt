package com.example.sevenwindstesttask.domain.coffeeShopsUseCase

import com.example.sevenwindstesttask.data.repository.Repository
import com.example.sevenwindstesttask.data.responses.coffeeShops.CoffeeShop
import javax.inject.Inject

class CoffeeShopsLocationUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun getCoffeeShops():List<CoffeeShop> =
        repository.getLocationCoffeeShops()

}