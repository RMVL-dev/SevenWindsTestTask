package com.example.sevenwindstesttask.helpers

import androidx.room.TypeConverter
import com.example.sevenwindstesttask.data.responses.coffeeShops.CoffeeShop
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonConverter {

    private val gson by lazy { Gson() }

    @TypeConverter
    fun coffeeShopsToJson(coffeeShops: List<CoffeeShop>?):String? =
        if (coffeeShops == null)
            null
        else try {
            gson.toJson(coffeeShops)
        }catch (e:Exception){
            null
        }

    @TypeConverter
    fun coffeeShopsJsonToList(value:String?): List<CoffeeShop>? {
        if (value == null)
            return null

        val typeEntity = object : TypeToken<List<CoffeeShop>?>() {}.type

        return try {
            gson.fromJson(value,typeEntity)
        }catch (e:Exception){
            null
        }
    }
}