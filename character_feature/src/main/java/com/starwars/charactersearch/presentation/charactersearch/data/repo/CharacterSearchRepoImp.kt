package com.starwars.charactersearch.presentation.charactersearch.data.repo

import com.starwars.charactersearch.presentation.charactersearch.data.source.CharacterSearchDataSourceImp
import com.starwars.domain.repository.CharacterSearchRepo
import com.starwars.domain.entities.CharacterSearchModel
import com.starwars.domain.entities.Output
import com.starwars.library.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CharacterSearchRepoImp(var characterSearchDataSourceImp: CharacterSearchDataSourceImp) : BaseRepository(),
    CharacterSearchRepo {
    override suspend fun searchForCharacter(query: String?): Output<CharacterSearchModel?> {
        return withContext(Dispatchers.IO)
        {
            return@withContext safeApiCall(
                call = { characterSearchDataSourceImp.searchForCharacter(query) }
            )
        }
    }

}