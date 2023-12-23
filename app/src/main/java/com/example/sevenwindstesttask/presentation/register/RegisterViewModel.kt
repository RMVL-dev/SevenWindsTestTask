package com.example.sevenwindstesttask.presentation.register

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindstesttask.domain.registerUseCase.RegisterUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val useCase: RegisterUseCase
):ViewModel(){


    private var _registerState = MutableLiveData<RegisterState>(RegisterState.Loading)
    val registerState: LiveData<RegisterState> = _registerState

    fun register(
        email: String,
        password: String
    ){

        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            _registerState.value = try {
                RegisterState.Success(
                    data = useCase.execute(
                        login = email,
                        password = password
                    )
                )
            }catch (e:Exception){
                RegisterState.Error
            }
        }
    }
}