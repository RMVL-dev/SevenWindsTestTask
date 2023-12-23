package com.example.sevenwindstesttask.di

import com.example.sevenwindstesttask.MainActivity
import com.example.sevenwindstesttask.presentation.map.MapActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun provideMainActivity():MainActivity

    @ContributesAndroidInjector
    abstract fun provideMapActivity():MapActivity
}