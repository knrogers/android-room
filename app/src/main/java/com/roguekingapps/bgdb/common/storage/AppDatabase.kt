package com.roguekingapps.bgdb.common.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roguekingapps.bgdb.boardgame.storage.BoardGame
import com.roguekingapps.bgdb.boardgame.storage.BoardGameDao

@Database(entities = [BoardGame::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun boardGameDao(): BoardGameDao
}