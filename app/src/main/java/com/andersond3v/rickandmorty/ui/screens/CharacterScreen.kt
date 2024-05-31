package com.andersond3v.rickandmorty.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.andersond3v.rickandmorty.R
import com.andersond3v.rickandmorty.data.local.entities.CharacterEntity
import com.andersond3v.rickandmorty.data.model.Character
import com.andersond3v.rickandmorty.ui.viewmodels.CharacterViewModel

@Composable
fun CharacterScreen(
    modifier: Modifier,
    characterViewModel: CharacterViewModel = viewModel(),
    handleNavigate: (id: Int) -> Unit
){
    BackHandler {}
    val characterList by characterViewModel.characters.collectAsState()
    val isLoading by characterViewModel.isLoading.collectAsState()

    if (isLoading) {
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
                items(characterList){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CharacterCard(character = it, characterViewModel ,handleNavigate)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterCard(character: Character, characterViewModel: CharacterViewModel, handleNavigate: (id: Int) -> Unit){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(6.dp),
        onClick = {handleNavigate(character.id)}
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .shadow(
                        elevation = 3.dp,
                        shape = CircleShape
                    )
                    .border(
                        shape = CircleShape,
                        color = Color.Gray,
                        width = 3.dp
                    )
                    .clip(CircleShape)
                    .size(100.dp)
            )
            Column(
                modifier = Modifier
                    .padding(6.dp)
                    .width(150.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = character.name,
                    fontWeight = FontWeight.W600,
                    fontStyle = FontStyle.Italic,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = character.species)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = character.status)
                Spacer(modifier = Modifier.padding(4.dp))
            }
            IconButton(
                onClick = {
                    characterViewModel.insertCharacter(character)
                },
            ){
                Icon(
                    painter = painterResource(id = R.drawable.download_24dp_fill0_wght400_grad0_opsz24),
                    contentDescription = "Download",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}