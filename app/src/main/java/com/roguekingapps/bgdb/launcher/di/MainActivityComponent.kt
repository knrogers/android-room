package com.roguekingapps.bgdb.launcher.di

import com.roguekingapps.bgdb.application.di.ApplicationComponent
import com.roguekingapps.bgdb.boardgame.di.BoardGamesViewModelModule
import com.roguekingapps.bgdb.common.di.ActivityScope
import com.roguekingapps.bgdb.launcher.ui.MainActivity
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
