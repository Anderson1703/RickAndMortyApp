package com.andersond3v.rickandmorty.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andersond3v.rickandmorty.data.model.Character
import com.andersond3v.rickandmorty.data.model.Episode
import com.andersond3v.rickandmorty.data.remote.RickAndMortyService
import com.andersond3v.rickandmorty.data.remote.repository.RickAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
     private val rickAndMortyRepository: RickAndMortyRepository = RickAndMortyRepository(RickAndMortyService.instance)
):ViewModel() {
    private val _character = MutableStateFlow<Character?>(null)
    val character: StateFlow<Character?> = _character.asStateFlow()

    private val _episode = MutableStateFlow<Episode?>(null)
    val episode: StateFlow<Episode?> = _episode.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun handleGetCharacter(id:Int){
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            rickAndMortyRepository.getCharacter(id)
                .onSuccess {
                    Log.d("get character success", "character: $it")
                    _character.value = it
                }
                .onFailure {
                    Log.d("get character failure", "failure: $it")
                }
        }
        _isLoading.value = false
    }

    fun handleGetEpisode(id:Int){
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            rickAndMortyRepository.getEpisode(id)
                .onSuccess {
                    Log.d("get episode success", "episode: $it")
                    _episode.value = it
                    _isLoading.value = false
                }.onFailure {
                    Log.d("get episode failure", "failure: $it")
                    _isLoading.value = false
                }
        }
    }
}