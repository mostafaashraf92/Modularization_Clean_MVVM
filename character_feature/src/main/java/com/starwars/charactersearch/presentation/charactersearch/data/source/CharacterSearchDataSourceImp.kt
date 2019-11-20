package com.starwars.charactersearch.presentation.charactersearch.data.source

import com.starwars.domain.entities.CharacterSearchModel
import repositories.CharacterSearchDataSource
import retrofit2.Response

class CharacterSearchDataSourceImp(private val characterSearchApi: CharacterSearchApi) :
    CharacterSearchDataSource<Response<CharacterSearchModel?>> {
    override suspend fun searchForCharacter(query: String?): Response<CharacterSearchModel?> {
        return characterSearchApi.getCharactersAsync(query).await()
    }


}