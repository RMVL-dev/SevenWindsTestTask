package com.example.sevenwindstesttask.presentation.order

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sevenwindstesttask.data.responses.order.Order
import com.example.sevenwindstesttask.helpers.JsonConverter

class OrderViewModel: ViewModel() {

    var orderList:List<Order>? = null
        private set

    fun parseOrder(json:String?){
        orderList = JsonConverter<List<Order>>().jsonToValue(json)

        Log.d("ORDER_RF", "$orderList")
    }


}