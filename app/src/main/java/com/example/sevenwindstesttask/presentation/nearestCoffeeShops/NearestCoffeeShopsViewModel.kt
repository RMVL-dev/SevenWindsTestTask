package com.example.sevenwindstesttask.presentation.nearestCoffeeShops

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindstesttask.domain.coffeeShopsUseCase.CoffeeShopsLocationUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class NearestCoffeeShopsViewModel @Inject constructor(
    private val useCase: CoffeeShopsLocationUseCase
):ViewModel() {

    private val _coffeeShops = MutableLiveData<CoffeeShopsState>()
    val coffeeShops:LiveData<CoffeeShopsState> = _coffeeShops

    fun getCoffeeShops(){
        viewModelScope.launch {
            _coffeeShops.value = CoffeeShopsState.Loading
            _coffeeShops.value = try {
                CoffeeShopsState.Success(
                    coffeeShops = useCase.getCoffeeShops()
                )
            }catch (e:Exception){
                CoffeeShopsState.Error
            }
        }
    }


}