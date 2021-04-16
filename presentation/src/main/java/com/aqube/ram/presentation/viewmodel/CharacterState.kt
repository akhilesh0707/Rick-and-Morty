package com.aqube.ram.presentation.viewmodel

import com.aqube.ram.domain.models.Character

sealed class CharacterState {
    object Init : CharacterState()
    object Loading : CharacterState()
    data class Error(var message: String) : CharacterState()
    data class CharacterListSuccess(var listOfCharacters: List<Character>) : CharacterState()
}
