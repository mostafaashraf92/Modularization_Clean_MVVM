package com.starwars.domain.usecase

import com.starwars.domain.entities.Output
import com.starwars.domain.entities.SpeciesModel
import com.starwars.domain.repository.CharacterSpeciesRepo

class GetSpeciesUseCase(private val characterSpeciesRepo: CharacterSpeciesRepo) :
    UseCase<SpeciesModel>() {

    suspend fun search(url: String?): Output<SpeciesModel?> {
        return characterSpeciesRepo.search(url)
    }
}