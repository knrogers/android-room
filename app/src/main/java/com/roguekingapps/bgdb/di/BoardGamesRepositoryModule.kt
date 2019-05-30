package com.roguekingapps.bgdb.di

import com.roguekingapps.bgdb.data.network.AppExecutors
import com.roguekingapps.bgdb.data.network.BoardGamesRepository
import com.roguekingapps.bgdb.data.network.BoardGamesRepositoryImpl
import com.roguekingapps.bgdb.data.network.BoardGamesService
import com.roguekingapps.bgdb.data.database.BoardGameDao
import dagger.Module
import dagger.Provides

@Module(includes = [BoardGamesServiceModule::class, DatabaseModule::class])
object BoardGamesRepositoryModule {

    @Provides
    @JvmStatic
    fun provideBoardGamesRepository(
        appExecutors: AppExecutors,
        service: BoardGamesService,
        boardGameDao: BoardGameDao
    ): BoardGamesRepository = BoardGamesRepositoryImpl(appExecutors, service, boardGameDao)

}