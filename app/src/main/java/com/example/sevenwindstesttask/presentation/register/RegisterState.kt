package com.example.sevenwindstesttask.presentation.register

import com.example.sevenwindstesttask.data.responses.register.RegisterResponse

sealed interface RegisterState{
    data class Success(val data: RegisterResponse):RegisterState

    object Error:RegisterState
    object Loading:RegisterState
}