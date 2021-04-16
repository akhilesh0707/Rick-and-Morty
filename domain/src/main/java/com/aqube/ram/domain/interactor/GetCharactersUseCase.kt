package com.aqube.ram.domain.interactor

import com.aqube.ram.domain.models.CharacterList
import com.aqube.ram.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetCharacterBaseUseCase = BaseUseCase<Unit, Flow<CharacterList>>

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : GetCharacterBaseUseCase {

    override suspend operator fun invoke(params: Unit) = characterRepository.getCharacters()
}