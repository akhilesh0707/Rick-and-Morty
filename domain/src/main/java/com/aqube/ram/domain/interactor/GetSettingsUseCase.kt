package com.aqube.ram.domain.interactor

import com.aqube.ram.domain.models.Settings
import com.aqube.ram.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetSettingsBaseUseCase = BaseUseCase<Unit, Flow<List<Settings>>>

class GetSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) : GetSettingsBaseUseCase {

    override suspend operator fun invoke(params: Unit) = settingsRepository.getSettings()
}