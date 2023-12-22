package com.example.sevenwindstesttask.data.requests.login

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("login")
    val login:String,
    @SerializedName("password")
    val password:String
)
