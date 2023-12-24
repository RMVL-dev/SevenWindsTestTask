package com.example.sevenwindstesttask.data.responses.coffee

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
    var quantity: Int = 0
)
