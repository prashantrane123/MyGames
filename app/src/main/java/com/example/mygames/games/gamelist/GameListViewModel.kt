package com.example.mygames.games.gamelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygames.games.data.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(var repository: GameListRepository) : ViewModel() {


    val gameListLiveData: MutableLiveData<List<Game>> = MutableLiveData()

    fun getGameList() {
        viewModelScope.launch(Dispatchers.IO) {
            gameListLiveData.postValue(repository.getGamesList())
        }
    }

}