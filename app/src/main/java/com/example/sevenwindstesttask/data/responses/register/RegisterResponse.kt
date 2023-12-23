package com.example.sevenwindstesttask.data.responses.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("tokenLifetime")
    val tokenLifetime:Long
)
