package com.starwars.domain.usecase

import com.starwars.domain.entities.CharacterSearchModel
import com.starwars.domain.entities.Output
import com.starwars.domain.repository.CharacterSearchRepo

class SearchCharacterUseCase(private val characterSearchRepo: CharacterSearchRepo) :
    UseCase<CharacterSearchModel>() {

    suspend fun searchForCharacters(querySearch: String?): Output<CharacterSearchModel?> {
        return characterSearchRepo.searchForCharacter(querySearch)
    }
}