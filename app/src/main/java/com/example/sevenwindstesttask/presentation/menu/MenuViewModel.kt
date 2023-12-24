package com.example.sevenwindstesttask.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sevenwindstesttask.data.responses.coffee.Coffee
import com.example.sevenwindstesttask.domain.menuUseCase.MenuUseCase
import com.example.sevenwindstesttask.data.responseState.ResponseState
import com.example.sevenwindstesttask.data.responses.coffee.toOrder
import com.example.sevenwindstesttask.data.responses.order.Order
import com.example.sevenwindstesttask.helpers.JsonConverter
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val useCase:MenuUseCase
): ViewModel() {

    private val _menu = MutableLiveData<ResponseState<List<Coffee>>>()
    val menu:LiveData<ResponseState<List<Coffee>>> = _menu

    private val _items = MutableLiveData<MutableList<MutableMap<Int, Int>>>()
    val items:LiveData<MutableList<MutableMap<Int, Int>>> = _items

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
    fun setMap(list:List<Coffee>){
        _items.value = emptyList<MutableMap<Int, Int>>().toMutableList()
        repeat(list.size){position ->
            _items.value?.add(mutableMapOf(list[position].id to 0))
        }
    }

    fun getOrder(list: List<Coffee>):String?{
        val orderList:MutableList<Order> = emptyList<Order>().toMutableList()

        repeat(list.size){position->
            _items.value?.get(position)?.get(list[position].id).let {quantity ->
                quantity?.let {
                    if (quantity!=0)
                        orderList.add(
                            list[position].toOrder(

                                quantity = it

                            )
                        )
                }
            }
        }
        return JsonConverter<List<Order>>().valueToJson(value = orderList)
    }


    companion object {
        private const val startUrl = "location/"
        private const val endUrl = "/menu"
    }

}