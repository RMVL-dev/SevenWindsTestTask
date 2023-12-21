package com.example.sevenwindstesttask.di

import com.example.sevenwindstesttask.ApplicationWithDI
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        FragmentModule::class
    ]
)
interface ApplicationComponent: AndroidInjector<ApplicationWithDI>{

    @Component.Factory
    interface Factory{
        fun create (@BindsInstance application: ApplicationWithDI):ApplicationComponent
    }

}