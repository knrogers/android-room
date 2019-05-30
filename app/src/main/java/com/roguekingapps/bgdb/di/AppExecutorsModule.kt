package com.roguekingapps.bgdb.di

import com.roguekingapps.bgdb.data.network.AppExecutors
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppExecutorsModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideAppExecutors(): AppExecutors =
        AppExecutors()

}