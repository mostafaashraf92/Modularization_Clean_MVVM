package com.starwars.domain.repository

import com.starwars.domain.entities.Output
import com.starwars.domain.entities.PlanetModel

interface CharacterPlanetRepo {

    suspend fun search(url: String?): Output<PlanetModel?>
}