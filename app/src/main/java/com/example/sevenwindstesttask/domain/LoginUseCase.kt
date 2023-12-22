package com.example.sevenwindstesttask.domain

import com.example.sevenwindstesttask.data.repository.Repository
import com.example.sevenwindstesttask.data.repository.SharedPref
import com.example.sevenwindstesttask.data.responses.login.LoginResponse
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: Repository,
    private val sharedPref: SharedPref
) {

    suspend fun execute(login:String, password:String): LoginResponse {
        val loginData = repository.login(login, password)
        sharedPref.userToken = loginData.token
        return loginData
    }

}