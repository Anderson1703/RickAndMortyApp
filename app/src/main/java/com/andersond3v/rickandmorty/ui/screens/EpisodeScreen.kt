package com.andersond3v.rickandmorty.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andersond3v.rickandmorty.data.model.Episode
import com.andersond3v.rickandmorty.ui.viewmodels.EpisodeViewModel

@Composable
fun EpisodeScreen(
    modifier: Modifier,
    episodeViewModel: EpisodeViewModel = viewModel(),
    handleNavigate: (id: Int) -> Unit
){
    BackHandler {}
    val episodesList by episodeViewModel.episodes.collectAsState()
    val isLoading by episodeViewModel.isLoading.collectAsState()

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
            .padding(top = 12.dp)) {
            LazyColumn {
                items(episodesList){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        EpisodeCard(episode = it, handleNavigate)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodeCard(episode: Episode, handleNavigate: (id: Int) -> Unit){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        onClick = {handleNavigate(episode.id)}
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(6.dp)
                    .fillMaxHeight()
            ) {
                Text(
                    text = episode.name,
                    fontWeight = FontWeight.W600,
                    fontStyle = FontStyle.Italic,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = episode.episode)
            }
        }
    }
}