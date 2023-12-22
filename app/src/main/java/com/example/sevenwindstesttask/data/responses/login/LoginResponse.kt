package com.example.sevenwindstesttask.data.responses.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token:String,
    @SerializedName("tokenLifetime")
    val tokenLifeTime:Long
)
