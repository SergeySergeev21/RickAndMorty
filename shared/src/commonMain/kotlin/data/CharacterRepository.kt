package data

import data.entity.CharactersResponse
import data.entity.toModel
import domain.CharacterModel
import domain.ICharacterRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

object CharacterRepository: ICharacterRepository {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun loadNewCharacters() : List<CharacterModel> {
        return withContext(Dispatchers.IO) {
            try {
                val result = httpClient.get("https://rickandmortyapi.com/api/character").body<CharactersResponse>()
                result.results.map { it.toModel() }
            } catch (e: Exception) {
                emptyList()
            }
        }
    }
}