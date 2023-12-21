package com.example.sevenwindstesttask.data.requests.register

import com.google.gson.annotations.SerializedName

data class RequestRegister(
    @SerializedName("login")
    val login:String,
    @SerializedName("password")
    val password: String
)
