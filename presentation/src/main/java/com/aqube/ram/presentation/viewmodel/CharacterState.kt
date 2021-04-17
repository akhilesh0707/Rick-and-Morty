package com.aqube.ram.presentation.viewmodel

import com.aqube.ram.domain.models.CharacterList

sealed class CharacterState {
    object Init : CharacterState()
    object Loading : CharacterState()
    data class Error(var message: Int) : CharacterState()
    data class Success(var characters: CharacterList) : CharacterState()
}
