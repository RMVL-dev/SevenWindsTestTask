package com.example.sevenwindstesttask.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindstesttask.domain.loginUseCase.LoginUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val useCase: LoginUseCase
):ViewModel(){

    private var _loginState = MutableLiveData<LoginState>()
    val loginState:LiveData<LoginState> = _loginState

    fun login(
        email:String,
        password:String
    ){
        viewModelScope.launch {
            _loginState.value = LoginState.Loading
            _loginState.value = try {
                LoginState.Success(
                    data = useCase.execute(
                        login = email,
                        password = password
                    )
                )
            }catch (e:Exception){
                LoginState.Error
            }
        }
    }


}