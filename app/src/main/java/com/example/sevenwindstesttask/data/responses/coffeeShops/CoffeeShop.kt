package com.example.sevenwindstesttask.data.responses.coffeeShops

import com.google.gson.annotations.SerializedName

data class CoffeeShop(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("point")
    val point:CoffeePoint
)
