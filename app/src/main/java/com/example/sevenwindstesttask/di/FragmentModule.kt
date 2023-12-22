package com.example.sevenwindstesttask.di

import com.example.sevenwindstesttask.presentation.login.LoginFragment
import com.example.sevenwindstesttask.presentation.register.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun registerFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment
}