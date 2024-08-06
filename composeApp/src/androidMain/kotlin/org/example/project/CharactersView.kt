package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import domain.CharacterModel
import presentation.CharacterController

@Composable
fun CharacterScreen() {
    val characters = CharacterController.characters.collectAsState().value
    ComposeCharactersView(characters = characters)

    LaunchedEffect(Unit) {
        CharacterController.loadNewCharacters()
    }
}

@Composable
fun ComposeCharactersView(characters: List<CharacterModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = characters,
            key = { it.id }
        ) { character->
            CharacterView(character)
        }
    }
}

@Composable
fun CharacterView(
    model: CharacterModel
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(Color.Gray.copy(alpha = 0.3f))
    ){
        AsyncImage(
            model = model.image,
            contentDescription = "characterImage",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = model.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        CharacterDescriptionValue("status: ", model.status)
        CharacterDescriptionValue("species: ", model.species)
        CharacterDescriptionValue("type: ", model.type)
        CharacterDescriptionValue("gender: ", model.gender)
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Composable
fun CharacterDescriptionValue(
    description: String,
    value: String
) {
    if (value.isNotEmpty()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = description,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Text(
                text = value,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
    }
}