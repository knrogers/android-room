package com.roguekingapps.bgdb.boardgame.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.roguekingapps.bgdb.boardgame.network.BoardGamesRepository
import com.roguekingapps.bgdb.common.network.Resource
import com.roguekingapps.bgdb.common.network.Status
import com.roguekingapps.bgdb.common.network.Status.LOADING
import com.roguekingapps.bgdb.boardgame.storage.BoardGame

class BoardGamesViewModel(private val boardGamesRepository: BoardGamesRepository) : ViewModel() {

    private val _status = MutableLiveData<Status>()

    val boardGames : LiveData<Resource<List<BoardGame>>> =
        Transformations.switchMap(_status) { boardGamesRepository.getBoardGames() }

    fun getBoardGames() {
        _status.value = LOADING
    }

}