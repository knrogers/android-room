package com.roguekingapps.bgdb.boardgame.network

import androidx.lifecycle.LiveData
import com.roguekingapps.bgdb.common.network.ApiResponse
import retrofit2.http.GET

interface BoardGamesService {

    @GET("xmlapi2/hot/")
    fun getBoardGames(): LiveData<ApiResponse<BoardGamesDto>>

}