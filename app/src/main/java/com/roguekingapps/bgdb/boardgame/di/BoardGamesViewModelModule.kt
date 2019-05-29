package com.roguekingapps.bgdb.boardgame.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.roguekingapps.bgdb.boardgame.network.BoardGamesRepository
import com.roguekingapps.bgdb.boardgame.viewmodel.BoardGamesViewModel
import com.roguekingapps.bgdb.common.di.ViewModelKey
import com.roguekingapps.bgdb.common.viewmodel.ViewModelFactory
import com.roguekingapps.bgdb.launcher.ui.MainActivity
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