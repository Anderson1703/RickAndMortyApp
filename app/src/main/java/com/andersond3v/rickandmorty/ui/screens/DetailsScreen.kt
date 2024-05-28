package com.andersond3v.rickandmorty.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.andersond3v.rickandmorty.data.model.Character
import com.andersond3v.rickandmorty.data.model.Episode
import com.andersond3v.rickandmorty.ui.viewmodels.DetailsViewModel

@Composable
fun DetailsScreen(
    id: Int,
    type: String,
    modifier: Modifier,
    detailsViewModel: DetailsViewModel = viewModel()
){
    if (type == "character"){
        detailsViewModel.handleGetCharacter(id)
    }
    if (type == "episode"){
        detailsViewModel.handleGetEpisode(id)
    }

    val character by detailsViewModel.character.collectAsState()
    val episode by detailsViewModel.episode.collectAsState()
    val isLoading by detailsViewModel.isLoading.collectAsState()

    if (isLoading){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }else{
        Column(modifier = modifier
            .fillMaxSize()
            .padding(top = 12.dp , start = 8.dp, end = 8.dp, bottom = 8.dp)) {
            if (type == "character"){
                CharacterDetails(character)
            }
            if (type == "episode"){
                EpisodeDetails(episode)
            }
        }
    }
}

@Composable
fun CharacterDetails(character: Character?){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxSize()
            .height(100.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            character?.let {
                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)

                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = character.name)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = character.status)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = character.species)
            }
        }
    }
}

@Composable
fun EpisodeDetails(episode: Episode?){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxSize()
            .height(100.dp),
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            episode?.let {
                Text(text = episode.name)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = episode.created)
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = episode.air_date)
            }
        }
    }
}