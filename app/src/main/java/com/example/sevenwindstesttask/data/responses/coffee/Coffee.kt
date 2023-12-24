package com.example.sevenwindstesttask.data.responses.coffee

import com.example.sevenwindstesttask.data.responses.order.Order
import com.google.gson.annotations.SerializedName

data class Coffee(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("imageURL")
    val imageURL: String,
    @SerializedName("price")
    val price: Int,
)


fun Coffee.toOrder(quantity:Int):Order{
    return Order(
        id = this.id,
        name = this.name,
        imageURL =this.imageURL,
        price = this.price,
        quantity = quantity
    )
}