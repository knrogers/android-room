package com.roguekingapps.bgdb.boardgame.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface BoardGamesService {

    @GET("xmlapi2/hot/")
    fun getBoardGames(): Deferred<Response<BoardGames>>

}