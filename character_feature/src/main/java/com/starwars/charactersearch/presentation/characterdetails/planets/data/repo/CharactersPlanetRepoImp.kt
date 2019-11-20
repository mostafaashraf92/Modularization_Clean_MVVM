package com.starwars.charactersearch.presentation.characterdetails.planets.data.repo

import com.starwars.charactersearch.presentation.characterdetails.planets.data.source.CharacterPlanetsDataSourceImp
import com.starwars.domain.repository.CharacterPlanetRepo
import com.starwars.domain.entities.Output
import com.starwars.domain.entities.PlanetModel
import com.starwars.library.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CharactersPlanetRepoImp(var characterPlanetsDataSourceImp: CharacterPlanetsDataSourceImp) : BaseRepository(),
    CharacterPlanetRepo {
    override suspend fun search(url: String?): Output<PlanetModel?> {
        return withContext(Dispatchers.IO)
        {
            return@withContext safeApiCall(
                call = { characterPlanetsDataSourceImp.searchForPlanets(url)}
            )
        }
    }

}