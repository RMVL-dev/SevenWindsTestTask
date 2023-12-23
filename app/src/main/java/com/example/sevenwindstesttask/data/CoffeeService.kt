package com.example.sevenwindstesttask.data

import com.example.sevenwindstesttask.data.requests.login.LoginRequest
import com.example.sevenwindstesttask.data.requests.register.RequestRegister
import com.example.sevenwindstesttask.data.responses.coffeeShops.CoffeeShop
import com.example.sevenwindstesttask.data.responses.login.LoginResponse
import com.example.sevenwindstesttask.data.responses.register.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CoffeeService {

    @POST("auth/register")
    suspend fun register(@Body requestRegister: RequestRegister): RegisterResponse

    @POST("auth/login")
    suspend fun login(@Body loginRequest:LoginRequest): LoginResponse

    @GET("locations")
    suspend fun getNearestCoffeeShops():List<CoffeeShop>

}