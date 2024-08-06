package data.entity

import domain.CharacterModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class CharactersResponse(
    @SerialName("results")
    val results: List<CharacterDataModel>
)

@Serializable
data class CharacterDataModel(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("status")
    val status: String?,
    @SerialName("species")
    val species: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("gender")
    val gender: String?,
    @SerialName("image")
    val image: String?
)

fun CharacterDataModel.toModel() = CharacterModel(
    id = this.id ?: Random.nextInt().toString(),
    name = this.name ?: "",
    status = this.status ?: "",
    species = this.species ?: "",
    type = this.type ?: "",
    gender = this.gender ?: "",
    image = this.image ?: ""
)


