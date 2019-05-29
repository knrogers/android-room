package com.roguekingapps.bgdb.boardgame.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface BoardGameDao {

    @Query("SELECT * FROM boardgames")
    fun getBoardGames() : LiveData<List<BoardGame>>

    @Insert(onConflict = REPLACE)
    fun insertAll(boardGames: List<BoardGame>)

}