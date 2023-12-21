package com.example.sevenwindstesttask.di

import com.example.sevenwindstesttask.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun provideMainActivity():MainActivity

}