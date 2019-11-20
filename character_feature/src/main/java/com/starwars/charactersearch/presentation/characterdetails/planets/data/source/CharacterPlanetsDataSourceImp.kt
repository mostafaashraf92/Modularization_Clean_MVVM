package com.starwars.charactersearch.presentation.characterdetails.planets.data.source

import com.starwars.domain.entities.PlanetModel
import repositories.CharacterPlanetsDataSource
import retrofit2.Response

class CharacterPlanetsDataSourceImp(private val characterPlanetApi: CharacterPlanetApi) :
    CharacterPlanetsDataSource<Response<PlanetModel?>> {
    override suspend fun searchForPlanets(query: String?): Response<PlanetModel?>{
        return characterPlanetApi.getDetailsAsync(query).await()
    }
}