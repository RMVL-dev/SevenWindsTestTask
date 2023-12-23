package com.example.sevenwindstesttask.presentation.login

import com.example.sevenwindstesttask.data.responses.login.LoginResponse

sealed interface LoginState{

    data class Success(val data: LoginResponse):LoginState

    object Error: LoginState

    object Loading: LoginState

}