package com.starwars.domain.repository

import com.starwars.domain.entities.Output
import com.starwars.domain.entities.SpeciesModel

interface CharacterSpeciesRepo {

    suspend fun search(url: String?): Output<SpeciesModel?>
}