package com.roguekingapps.bgdb.boardgame.network

import com.roguekingapps.bgdb.boardgame.network.ResponseResult.Error
import com.roguekingapps.bgdb.boardgame.network.ResponseResult.Success
import retrofit2.Response

class BoardGamesRepositoryImpl(private val service: BoardGamesService) : BoardGamesRepository {

    override suspend fun getBoardGames(): ResponseResult<BoardGames> =
        awaitResponse(object : ResponseHandler<BoardGames> {

            override suspend fun doRequest(): Response<BoardGames> = service.getBoardGames().await()

            override fun onSuccess(response: Response<BoardGames>) = Success(response.body() as BoardGames)

            override fun onFailure(): Error = Error()

            override fun onException(): Error = Error()

        })

}

interface BoardGamesRepository {

    suspend fun getBoardGames(): ResponseResult<BoardGames>

}