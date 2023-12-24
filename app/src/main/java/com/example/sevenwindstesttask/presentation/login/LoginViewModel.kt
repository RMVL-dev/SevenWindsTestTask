package com.example.sevenwindstesttask.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindstesttask.data.responses.login.LoginResponse
import com.example.sevenwindstesttask.domain.loginUseCase.LoginUseCase
import com.example.sevenwindstesttask.data.responseState.ResponseState
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase
):ViewModel(){

    private var _loginState = MutableLiveData<ResponseState<LoginResponse>>()
    val loginState:LiveData<ResponseState<LoginResponse>> = _loginState

    fun login(
        email:String,
        password:String
    ){
        viewModelScope.launch {
            _loginState.value = ResponseState.Loading()
            _loginState.value = try {
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