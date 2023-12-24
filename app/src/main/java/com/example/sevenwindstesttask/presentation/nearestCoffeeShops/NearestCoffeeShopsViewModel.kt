package com.example.sevenwindstesttask.presentation.nearestCoffeeShops

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindstesttask.data.responses.coffeeShops.CoffeeShop
import com.example.sevenwindstesttask.domain.coffeeShopsUseCase.CoffeeShopsLocationUseCase
import com.example.sevenwindstesttask.data.responseState.ResponseState
import kotlinx.coroutines.launch
import javax.inject.Inject

class NearestCoffeeShopsViewModel @Inject constructor(
    private val useCase: CoffeeShopsLocationUseCase
):ViewModel() {

    private val _coffeeShops = MutableLiveData<ResponseState<List<CoffeeShop>>>()
    val coffeeShops:LiveData<ResponseState<List<CoffeeShop>>> = _coffeeShops

    fun getCoffeeShops(){
        viewModelScope.launch {
            _coffeeShops.value = ResponseState.Loading()
            _coffeeShops.value = try {
                ResponseState.Success(
                    data = useCase.getCoffeeShops()
                )
            }catch (e:Exception){
                ResponseState.Error()
            }
        }
    }


}