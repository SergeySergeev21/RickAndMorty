package org.example.project

import CharacterModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun ComposePreview() {
    val characters = listOf(
        CharacterModel(
            id = "1",
            name = "Rick",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            image = ""
        ),
        CharacterModel(
            id = "2",
            name = "Morty",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            image = ""
        ),
        CharacterModel(
            id = "3",
            name = "Summer",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Woman",
            image = ""
        ),
        CharacterModel(
            id = "4",
            name = "Beth",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Woman",
            image = ""
        )
    )

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
        Image(
            painter = painterResource(R.drawable.rick),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )
        Text(
            text = model.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
        CharacterDescriptionValue("status: ", model.status)
        CharacterDescriptionValue("species: ", model.species)
        CharacterDescriptionValue("type: ", model.type)
        CharacterDescriptionValue("gender: ", model.gender)
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
                textAlign = TextAlign.Center
            )
        }
    }
}