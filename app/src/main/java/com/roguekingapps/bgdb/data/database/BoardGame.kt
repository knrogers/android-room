package com.roguekingapps.bgdb.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "boardgames")
data class BoardGame(
    @PrimaryKey val rank: String,
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "year") val year: String?,
    @ColumnInfo(name = "thumbnail_url") val thumbnailUrl: String?
)