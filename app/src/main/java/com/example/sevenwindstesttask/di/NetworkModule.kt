package com.example.sevenwindstesttask.di

import com.example.sevenwindstesttask.data.interceptor.HeaderInterceptor
import com.example.sevenwindstesttask.data.repository.SharedPref
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.example.sevenwindstesttask.data.CoffeeService

@Module
class NetworkModule {

    companion object{
        private const val BASE_URL = "http://147.78.66.203:3210"
    }

    @Provides
    fun provideOkHttp(
        preferencesStorage: SharedPref
    ) = OkHttpClient.Builder().apply {
        addInterceptor(HeaderInterceptor(preferenceStorage = preferencesStorage))
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        addInterceptor(loggingInterceptor)
    }
        .connectTimeout(20000L, TimeUnit.MILLISECONDS)
        .readTimeout(20000L, TimeUnit.MILLISECONDS)
        .writeTimeout(20000L, TimeUnit.MILLISECONDS)
        .build()


    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gson:Gson
    ) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()

    @Provides
    fun provideGson():Gson = GsonBuilder().create()


    @Provides
    fun provideHttpclient(retrofit: Retrofit): CoffeeService =
        retrofit.create(CoffeeService::class.java)
}