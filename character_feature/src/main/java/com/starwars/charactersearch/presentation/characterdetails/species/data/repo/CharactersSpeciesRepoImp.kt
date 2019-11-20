package com.starwars.charactersearch.presentation.characterdetails.species.data.repo

import com.starwars.charactersearch.presentation.characterdetails.species.data.source.CharacterSpeciesDataSourceImp
import com.starwars.domain.entities.SpeciesModel
import com.starwars.library.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CharactersSpeciesRepoImp(var characterSpeciesDataSourceImp: CharacterSpeciesDataSourceImp) :
    BaseRepository(),
    com.starwars.domain.repository.CharacterSpeciesRepo {
    override suspend fun search(url: String?): com.starwars.domain.entities.Output<SpeciesModel?> {

        return withContext(Dispatchers.IO)
        {
            return@withContext safeApiCall(
                call = { characterSpeciesDataSourceImp.searchForSpecies(url) }
            )
        }
    }

}