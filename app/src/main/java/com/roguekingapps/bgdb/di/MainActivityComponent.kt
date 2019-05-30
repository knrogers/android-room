package com.roguekingapps.bgdb.di

import com.roguekingapps.bgdb.ui.launcher.MainActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [BoardGamesViewModelModule::class])
interface MainActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance activity: MainActivity, applicationComponent: ApplicationComponent): MainActivityComponent
    }

    fun inject(activity: MainActivity)

}
