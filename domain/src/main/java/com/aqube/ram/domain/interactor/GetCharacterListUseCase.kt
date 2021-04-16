package com.aqube.ram.domain.interactor

import com.aqube.ram.domain.models.CharacterList
import com.aqube.ram.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetCharacterListBaseUseCase = BaseUseCase<Unit, Flow<CharacterList>>

class GetCharacterListUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : GetCharacterListBaseUseCase {

    override suspend operator fun invoke(params: Unit) = characterRepository.getCharacters()
}