package com.andersond3v.rickandmorty.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andersond3v.rickandmorty.data.model.Episode
import com.andersond3v.rickandmorty.data.remote.RickAndMortyService
import com.andersond3v.rickandmorty.data.remote.repository.RickAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EpisodeViewModel(
    private val rickAndMortyRepository: RickAndMortyRepository = RickAndMortyRepository(RickAndMortyService.instance)
): ViewModel() {
    private val _episodes = MutableStateFlow<List<Episode>>(emptyList())
    val episodes: StateFlow<List<Episode>> = _episodes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun handleGetAllEpisodes (page:Int){
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            rickAndMortyRepository.getEpisodes(page)
                .onSuccess {
                    Log.d("get episodes success", "episodes: $it")
                    _episodes.value = it
                    _isLoading.value = false
                }
                .onFailure {
                    Log.d("get episodes failure", "failure: $it")
                    _isLoading.value = false
                }
        }
    }

    init {
        handleGetAllEpisodes(1)
    }
}