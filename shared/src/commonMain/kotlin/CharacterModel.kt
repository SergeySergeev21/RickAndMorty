data class CharacterModel(
    val id: String,
    val image: String,
    val name: String,
    val status: Status,
    val species: String,
    val lastKnownLocation: String,
    val firstSeenIn: String
)

enum class Status {
    ALIVE, DEAD, UNKNOWN
}
