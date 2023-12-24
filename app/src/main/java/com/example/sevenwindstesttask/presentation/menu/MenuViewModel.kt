package com.example.sevenwindstesttask.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindstesttask.data.responses.coffee.Coffee
import com.example.sevenwindstesttask.domain.menuUseCase.MenuUseCase
import com.example.sevenwindstesttask.data.responseState.ResponseState
import com.example.sevenwindstesttask.helpers.JsonConverter
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val useCase:MenuUseCase
): ViewModel() {

    private val _menu = MutableLiveData<ResponseState<List<Coffee>>>()
    val menu:LiveData<ResponseState<List<Coffee>>> = _menu

    fun getMenu(id:Int){
        viewModelScope.launch {
            val url = "$startUrl$id$endUrl"
            _menu.value = ResponseState.Loading()
            _menu.value = try {
                ResponseState.Success(
                    data = useCase.getMenu(url = url)
                )
            }catch (e:Exception){
                ResponseState.Error()
            }
        }
    }

    fun getOrder(list: List<Coffee>):String?{
        val orderList:MutableList<Coffee> = emptyList<Coffee>().toMutableList()
        repeat(list.size){position->
            if (list[position].quantity != 0){
                orderList.add(list[position])
            }
        }
        return JsonConverter<List<Coffee>>().valueToJson(value = orderList)
    }

    fun increase(position:Int){
        (_menu.value as ResponseState.Success).data[position].quantity++
    }

    fun decrease(position: Int){
        if ((_menu.value as ResponseState.Success).data[position].quantity > 0){
            (_menu.value as ResponseState.Success).data[position].quantity--
        }
    }

    companion object {
        private const val startUrl = "location/"
        private const val endUrl = "/menu"
    }

}