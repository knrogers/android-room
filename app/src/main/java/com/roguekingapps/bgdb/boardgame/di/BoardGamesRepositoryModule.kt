package com.roguekingapps.bgdb.boardgame.di

import com.roguekingapps.bgdb.common.network.AppExecutors
import com.roguekingapps.bgdb.boardgame.network.BoardGamesRepository
import com.roguekingapps.bgdb.boardgame.network.BoardGamesRepositoryImpl
import com.roguekingapps.bgdb.boardgame.network.BoardGamesService
import com.roguekingapps.bgdb.boardgame.storage.BoardGameDao
import com.roguekingapps.bgdb.common.di.DatabaseModule
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