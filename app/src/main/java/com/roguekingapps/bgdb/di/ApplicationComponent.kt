package com.roguekingapps.bgdb.di

import android.content.Context
import com.roguekingapps.bgdb.application.BGDbApplication
import com.roguekingapps.bgdb.util.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelFactoryModule::class, AppExecutorsModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: BGDbApplication, @BindsInstance context: Context): ApplicationComponent
    }

    val viewModelFactory: ViewModelFactory

}
