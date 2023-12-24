package com.example.sevenwindstesttask.helpers

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonConverter<T>{

    private val gson by lazy { Gson() }

    @TypeConverter
    fun valueToJson(value: T?):String? =
        if (value == null)
            null
        else try {
            gson.toJson(value)
        }catch (e:Exception){
            null
        }

    @TypeConverter
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
}