package com.roguekingapps.bgdb.ui.boardgame

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.roguekingapps.bgdb.data.network.BoardGamesRepository
import com.roguekingapps.bgdb.data.network.Resource
import com.roguekingapps.bgdb.data.network.Status
import com.roguekingapps.bgdb.data.network.Status.LOADING
import com.roguekingapps.bgdb.data.database.BoardGame

class BoardGamesViewModel(private val boardGamesRepository: BoardGamesRepository) : ViewModel() {

    private val _status = MutableLiveData<Status>()

    val boardGames : LiveData<Resource<List<BoardGame>>> =
        Transformations.switchMap(_status) { boardGamesRepository.getBoardGames() }

    fun getBoardGames() {
        _status.value = LOADING
    }

}