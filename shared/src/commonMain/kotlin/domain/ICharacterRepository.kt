package domain

interface ICharacterRepository {
    suspend fun loadNewCharacters() : List<CharacterModel>
}