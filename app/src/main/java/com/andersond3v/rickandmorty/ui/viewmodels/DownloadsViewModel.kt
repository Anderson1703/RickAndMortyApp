package com.andersond3v.rickandmorty.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andersond3v.rickandmorty.data.local.entities.CharacterEntity
import com.andersond3v.rickandmorty.data.local.entities.EpisodeEntity
import com.andersond3v.rickandmorty.data.local.repository.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DownloadsViewModel(
    private val downloadsRepository: DBRepository
): ViewModel() {

    private val _characters = MutableStateFlow<List<CharacterEntity>>(emptyList())
    val characters: StateFlow<List<CharacterEntity>> = _characters.asStateFlow()

    private val _episodes = MutableStateFlow<List<EpisodeEntity>>(emptyList())
    val episodes: StateFlow<List<EpisodeEntity>> = _episodes.asStateFlow()

    fun getCharacters(){
        viewModelScope.launch(Dispatchers.IO) {
            downloadsRepository.getCharacters().collect{
                _characters.value = it
            }
        }
    }

    fun insertCharacter(character: CharacterEntity){
        viewModelScope.launch(Dispatchers.IO) {
            downloadsRepository.insertCharacters(character)
        }
    }

    fun getEpisodes(){
        viewModelScope.launch(Dispatchers.IO) {
            downloadsRepository.getEpisodes().collect{
                _episodes.value = it
            }
        }
    }

    fun insertEpisode(episode: EpisodeEntity){
        viewModelScope.launch(Dispatchers.IO) {
            downloadsRepository.insertEpisodes(episode)
        }
    }

  init {
        getCharacters()
        getEpisodes()
    }
}