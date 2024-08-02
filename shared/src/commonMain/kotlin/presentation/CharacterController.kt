package presentation

import data.CharacterRepository
import domain.CharacterModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

object CharacterController {
    private val scope = CoroutineScope(Dispatchers.Main)
    private val _characters = MutableStateFlow<List<CharacterModel>>(emptyList())
    val characters get() = _characters.asStateFlow()

    fun loadNewCharacters() {
        scope.launch {
            val newList = CharacterRepository.loadNewCharacters()
            _characters.value = characters.value + newList
        }
    }
}