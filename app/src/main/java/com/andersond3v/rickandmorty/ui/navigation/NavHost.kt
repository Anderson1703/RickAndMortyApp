package com.andersond3v.rickandmorty.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.andersond3v.rickandmorty.R
import com.andersond3v.rickandmorty.ui.screens.DetailsScreen
import com.andersond3v.rickandmorty.ui.screens.EpisodeScreen
import com.andersond3v.rickandmorty.ui.screens.CharacterScreen
import com.andersond3v.rickandmorty.ui.screens.DownloadsScreen

@Composable
fun ScaffoldRickAndMortyApp(){
    val navController = rememberNavController()
    Scaffold (
        topBar = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text ="RickAndMorty",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W800
                )
                Image(
                    painter = painterResource(id = R.drawable.icon),
                    contentDescription = "RickAndMorty",
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
                        .size(50.dp)
                )
            }
        },
        content = {padding->
            NavHost(navController = navController, startDestination = Character){
                composable<Character>{
                    CharacterScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)) {
                        navController.navigate(Details(it,"character"))
                    }
                }
                composable<Episode>{
                    EpisodeScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)) {
                        navController.navigate(Details(it,"episode"))
                    }
                }
                composable<Details>{backStackEntry->
                    val item : Details = backStackEntry.toRoute()
                    DetailsScreen(item.id, item.type,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    )
                }
                composable<Downloads>{
                    DownloadsScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    )
                }
            }
        },
        bottomBar = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate(Character)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.home_24px),
                        contentDescription = "Home",
                    )
                }

                IconButton(onClick = {
                    navController.navigate(Episode)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.movie_24px),
                        contentDescription = "Episode",
                    )
                }

                IconButton(onClick = {
                    navController.navigate(Downloads)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.download_24dp_fill0_wght400_grad0_opsz24),
                        contentDescription = "Favorites",
                    )
                }
            }
        }
    )
}