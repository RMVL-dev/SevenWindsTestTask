package com.example.sevenwindstesttask.data.responses

import com.google.gson.annotations.SerializedName

class BaseResponse<T> (
    @SerializedName("data")
    val data: T
)