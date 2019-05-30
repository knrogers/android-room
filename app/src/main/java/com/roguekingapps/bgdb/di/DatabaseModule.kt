package com.roguekingapps.bgdb.di

import android.content.Context
import androidx.room.Room
import com.roguekingapps.bgdb.data.database.BoardGameDao
import com.roguekingapps.bgdb.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context, AppDatabase::class.java, "bgdb"
        ).build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideBoardGameDao(database: AppDatabase): BoardGameDao = database.boardGameDao()

}