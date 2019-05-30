package com.roguekingapps.bgdb.data.network

import androidx.lifecycle.LiveData
import retrofit2.http.GET

interface BoardGamesService {

    @GET("xmlapi2/hot/")
    fun getBoardGames(): LiveData<ApiResponse<BoardGamesDto>>

}