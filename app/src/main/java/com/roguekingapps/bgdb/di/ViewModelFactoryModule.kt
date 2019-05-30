package com.roguekingapps.bgdb.di

import androidx.lifecycle.ViewModel
import com.roguekingapps.bgdb.util.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module(includes = [BoardGamesViewModelModule::class, BoardGamesRepositoryModule::class])
object ViewModelFactoryModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelFactory = ViewModelFactory(providers)

}