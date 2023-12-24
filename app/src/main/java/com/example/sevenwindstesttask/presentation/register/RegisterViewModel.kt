package com.example.sevenwindstesttask.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindstesttask.data.responses.register.RegisterResponse
import com.example.sevenwindstesttask.domain.registerUseCase.RegisterUseCase
import com.example.sevenwindstesttask.data.responseState.ResponseState
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val useCase: RegisterUseCase
):ViewModel(){


    private var _registerState = MutableLiveData<ResponseState<RegisterResponse>>()
    val registerState: LiveData<ResponseState<RegisterResponse>> = _registerState

    fun register(
        email: String,
        password: String
    ){

        viewModelScope.launch {
            _registerState.value = ResponseState.Loading()
            _registerState.value = try {
                ResponseState.Success(
                    data = useCase.execute(
                        login = email,
                        password = password
                    )
                )
            }catch (e:Exception){
                ResponseState.Error()
            }
        }
    }
}