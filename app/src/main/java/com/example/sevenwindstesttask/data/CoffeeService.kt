package com.example.sevenwindstesttask.data

import com.example.sevenwindstesttask.data.responses.BaseResponse
import com.example.sevenwindstesttask.data.requests.register.RequestRegister
import com.example.sevenwindstesttask.data.responses.register.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.PUT

interface CoffeeService {

    @PUT("/auth/register")
    suspend fun register(@Body requestRegister: RequestRegister): RegisterResponse
}