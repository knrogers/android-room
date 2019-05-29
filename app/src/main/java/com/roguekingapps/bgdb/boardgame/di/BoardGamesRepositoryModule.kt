package com.roguekingapps.bgdb.boardgame.di

import com.roguekingapps.bgdb.boardgame.network.BoardGamesRepository
import com.roguekingapps.bgdb.boardgame.network.BoardGamesRepositoryImpl
import com.roguekingapps.bgdb.boardgame.network.BoardGamesService
import dagger.Module
import dagger.Provides

@Module(includes = [BoardGamesServiceModule::class])
object BoardGamesRepositoryModule {

    @Provides
    @JvmStatic
    fun provideBoardGamesRepository(service: BoardGamesService): BoardGamesRepository =
        BoardGamesRepositoryImpl(service)

}