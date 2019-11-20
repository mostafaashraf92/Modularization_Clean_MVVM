package com.starwars.charactersearch.presentation.characterdetails.species.data.source

import com.starwars.domain.entities.SpeciesModel
import repositories.CharacterSpeciesDataSource
import retrofit2.Response

class CharacterSpeciesDataSourceImp(private val characterSpeciesApi: CharacterSpeciesApi) :
    CharacterSpeciesDataSource<Response<SpeciesModel?>> {
    override suspend fun searchForSpecies(query: String?): Response<SpeciesModel?> {
        return characterSpeciesApi.getDetailsAsync(query).await()
    }

}