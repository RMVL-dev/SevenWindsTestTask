package com.example.sevenwindstesttask.data.repository

import com.example.sevenwindstesttask.data.CoffeeService
import com.example.sevenwindstesttask.data.requests.login.LoginRequest
import com.example.sevenwindstesttask.data.requests.register.RequestRegister
import com.example.sevenwindstesttask.data.responses.login.LoginResponse
import com.example.sevenwindstesttask.data.responses.register.RegisterResponse
import javax.inject.Inject

class Repository @Inject constructor(
    private val coffeeService: CoffeeService
) {

    suspend fun login(login: String, password:String): LoginResponse =
        coffeeService.login(LoginRequest(login,password))

    suspend fun register(login:String, password: String): RegisterResponse =
        coffeeService.register(RequestRegister(login, password))

}