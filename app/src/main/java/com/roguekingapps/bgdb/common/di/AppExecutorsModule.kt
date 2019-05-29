package com.roguekingapps.bgdb.common.di

import com.roguekingapps.bgdb.common.network.AppExecutors
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