package com.example.sevenwindstesttask.di

import android.content.Context
import com.example.sevenwindstesttask.ApplicationWithDI
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideApplicationContext(application:ApplicationWithDI): Context =
        application.applicationContext

}