package com.andersond3v.rickandmorty.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andersond3v.rickandmorty.data.model.Character
import com.andersond3v.rickandmorty.data.remote.RickAndMortyService
import com.andersond3v.rickandmorty.data.remote.repository.RickAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val rickAndMortyRepository: RickAndMortyRepository = RickAndMortyRepository(RickAndMortyService.instance)
):ViewModel() {
    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters:StateFlow<List<Character>> = _characters.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun handleGetAllCharacters(page:Int){
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            rickAndMortyRepository.getCharacters(page)
                .onSuccess {
                    Log.d("get characters success", "characters: $it")
                    _characters.value = it
                    _isLoading.value = false
                }
                .onFailure {
                    Log.d("get characters failure", "failure: $it")
                    _isLoading.value = false
                }
        }
    }

    init {
        handleGetAllCharacters(1)
    }
}