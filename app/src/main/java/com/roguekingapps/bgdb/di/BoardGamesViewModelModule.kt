package com.roguekingapps.bgdb.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.roguekingapps.bgdb.data.network.BoardGamesRepository
import com.roguekingapps.bgdb.ui.boardgame.BoardGamesViewModel
import com.roguekingapps.bgdb.util.ViewModelFactory
import com.roguekingapps.bgdb.ui.launcher.MainActivity
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
object BoardGamesViewModelModule {

    @Provides
    @JvmStatic
    fun provideBoardGamesViewModel(activity: MainActivity, factory: ViewModelFactory) : BoardGamesViewModel =
        ViewModelProviders.of(activity, factory).get(BoardGamesViewModel::class.java)

    @Provides
    @IntoMap
    @ViewModelKey(BoardGamesViewModel::class)
    @JvmStatic
    fun provideBoardGamesViewModelForMap(repository: BoardGamesRepository): ViewModel =
        BoardGamesViewModel(repository)

}