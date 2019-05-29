package com.roguekingapps.bgdb.boardgame.di

import com.roguekingapps.bgdb.boardgame.network.BoardGamesService
import com.roguekingapps.bgdb.common.network.LiveDataCallAdapterFactory
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object BoardGamesServiceModule {

    @Provides
    @JvmStatic
    fun provideBoardGamesService(): BoardGamesService =
        Retrofit.Builder()
            .baseUrl("http://www.boardgamegeek.com/")
            .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(BoardGamesService::class.java)

}