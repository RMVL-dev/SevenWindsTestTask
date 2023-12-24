package com.example.sevenwindstesttask.data.responses.order

import com.google.gson.annotations.SerializedName

data class Order(
    val id: Int,
    val name: String,
    val imageURL: String,
    val price: Int,
    val quantity:Int
)
