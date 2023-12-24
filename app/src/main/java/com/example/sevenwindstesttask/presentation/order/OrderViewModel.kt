package com.example.sevenwindstesttask.presentation.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sevenwindstesttask.data.responses.coffee.Coffee
import com.example.sevenwindstesttask.helpers.JsonConverter

class OrderViewModel: ViewModel() {

    private val _orderList = MutableLiveData<MutableList<Coffee>>()
    val orderList:LiveData<MutableList<Coffee>> get() = _orderList

    fun parseOrder(json:String?){
        _orderList.value = JsonConverter<MutableList<Coffee>>().jsonCoffeeToValue(json)
    }

    fun increase(position:Int){
        val counter = _orderList.value?.get(position)?.quantity?.plus(1)
        if (counter != null) {
            _orderList.value?.get(position)?.quantity = counter
        }
    }

    fun decrease(position:Int){
        val counter = _orderList.value?.get(position)?.quantity
        if (counter != null && counter > 0) {
            _orderList.value?.get(position)?.quantity = counter.minus(1)
            if (_orderList.value?.get(position)?.quantity == 0){
                _orderList.value?.removeAt(position)
            }
        }
    }

    fun check(){
        _orderList.value = emptyList<Coffee>().toMutableList()
    }
}