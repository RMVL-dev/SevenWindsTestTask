package com.example.sevenwindstesttask.domain.registerUseCase

import com.example.sevenwindstesttask.data.repository.Repository
import com.example.sevenwindstesttask.data.repository.SharedPref
import com.example.sevenwindstesttask.data.responses.register.RegisterResponse
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: Repository,
    private val sharedPref: SharedPref
) {
    suspend fun execute(login: String, password: String): RegisterResponse {
        val registerData = repository.register(login, password)
        sharedPref.userToken = registerData.token
        return registerData
    }
}