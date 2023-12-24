package com.example.sevenwindstesttask.helpers

import com.example.sevenwindstesttask.data.responses.coffee.Coffee
import com.example.sevenwindstesttask.data.responses.coffeeShops.CoffeeShop
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonConverter<T>{

    private val gson by lazy { Gson() }

    fun valueToJson(value: T?):String? =
        if (value == null)
            null
        else try {
            gson.toJson(value)
        }catch (e:Exception){
            null
        }

    fun jsonToValue(value:String?): T? {
        if (value == null)
            return null

        val typeEntity = object : TypeToken<T?>() {}.type

        return try {
            gson.fromJson(value,typeEntity)
        }catch (e:Exception){
            null
        }
    }

    /**
     * Не успел разобобраться пока писал, но парсинг json list<coffee> не работает с дженериками
     * поэтому такой костыль, есть подозрения что парсить list нужно иначе
     */
    fun jsonCoffeeToValue(value:String?): MutableList<Coffee>? {
        if (value == null)
            return null

        val typeEntity = object : TypeToken<MutableList<Coffee>?>() {}.type

        return try {
            gson.fromJson(value,typeEntity)
        }catch (e:Exception){
            null
        }
    }

    fun jsonCoffeeShopsToValue(value:String?): List<CoffeeShop>? {
        if (value == null)
            return null

        val typeEntity = object : TypeToken<MutableList<CoffeeShop>?>() {}.type

        return try {
            gson.fromJson(value,typeEntity)
        }catch (e:Exception){
            null
        }
    }

}