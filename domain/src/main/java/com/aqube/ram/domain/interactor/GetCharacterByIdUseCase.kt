package com.aqube.ram.domain.interactor

import com.aqube.ram.domain.models.Character
import com.aqube.ram.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

typealias GetCharacterByIdBaseUseCase = BaseUseCase<Int, Flow<Character>>

class GetCharacterByIdUseCase(
    private val characterRepository: CharacterRepository
) : GetCharacterByIdBaseUseCase {

    override suspend operator fun invoke(params: Int) = characterRepository.getCharacter(params)
}