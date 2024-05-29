package com.andersond3v.rickandmorty.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
import com.andersond3v.rickandmorty.data.local.entities.CharacterEntity
//import com.andersond3v.rickandmorty.ui.viewmodels.DownloadsViewModel

@Composable
fun DownloadsScreen(
    modifier: Modifier,
    ///downloadsViewModel: DownloadsViewModel = viewModel()
) {
    //val characterList by downloadsViewModel.characters.collectAsState()
    val characterList = listOf<List<CharacterEntity>>(emptyList())


    Column(modifier = modifier
        .fillMaxSize()
        .padding(top = 12.dp)) {
        LazyColumn {
            items(characterList){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                  Text("test")
                }
            }
        }
    }
}

