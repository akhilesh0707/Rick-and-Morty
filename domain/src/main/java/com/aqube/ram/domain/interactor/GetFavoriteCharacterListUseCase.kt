package com.aqube.ram.domain.interactor

import com.aqube.ram.domain.models.Character
import com.aqube.ram.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetFavoriteCharacterListBaseUseCase = BaseUseCase<Unit, Flow<List<Character>>>

class GetFavoriteCharacterListUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : GetFavoriteCharacterListBaseUseCase {

    override suspend operator fun invoke(params: Unit) = characterRepository.getBookMarkedCharacters()
}