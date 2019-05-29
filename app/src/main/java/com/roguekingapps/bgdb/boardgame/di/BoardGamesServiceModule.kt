package com.roguekingapps.bgdb.boardgame.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.roguekingapps.bgdb.boardgame.network.BoardGamesService
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
            .baseUrl("https://www.boardgamegeek.com/")
            .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(BoardGamesService::class.java)

}