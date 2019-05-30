package com.roguekingapps.bgdb.data.network

import androidx.lifecycle.LiveData
import com.roguekingapps.bgdb.data.database.BoardGame
import com.roguekingapps.bgdb.data.database.BoardGameDao

class BoardGamesRepositoryImpl(
    private val appExecutors: AppExecutors,
    private val service: BoardGamesService,
    private val boardGameDao: BoardGameDao
) : BoardGamesRepository {

    override fun getBoardGames() : LiveData<Resource<List<BoardGame>>> {
        return object : NetworkBoundResource<List<BoardGame>, BoardGamesDto>(appExecutors) {

            override fun saveCallResult(data: BoardGamesDto) {
                val boardGames = data.boardGameDtos.map {
                    BoardGame(it.rank, it.id, it.name, it.year, it.thumbnailUrl)
                }
                boardGameDao.insertAll(boardGames)
            }

            override fun shouldFetch(data: List<BoardGame>?): Boolean = true

            override fun loadFromDb(): LiveData<List<BoardGame>> = boardGameDao.getBoardGames()

            override fun createCall(): LiveData<ApiResponse<BoardGamesDto>> = service.getBoardGames()

        }.asLiveData()
    }

}

interface BoardGamesRepository {

    fun getBoardGames() : LiveData<Resource<List<BoardGame>>>

}