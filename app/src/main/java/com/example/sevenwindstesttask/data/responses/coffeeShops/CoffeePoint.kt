package com.example.sevenwindstesttask.data.responses.coffeeShops

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class CoffeePoint(
    @SerializedName("latitude")
    val latitude:BigDecimal,
    @SerializedName("longitude")
    val longitude:BigDecimal
)


