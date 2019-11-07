package com.starwars.domain.usecase

import com.starwars.domain.entities.Output
import com.starwars.domain.entities.PlanetModel
import com.starwars.domain.repository.CharacterPlanetRepo

class GetPlanetUseCase(private val charactersPlanetRepo: CharacterPlanetRepo) :
    UseCase<PlanetModel>() {

    suspend fun search(url: String?): Output<PlanetModel?> {
        return charactersPlanetRepo.search(url)
    }
}