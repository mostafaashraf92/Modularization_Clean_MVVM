package com.starwars.domain.repository

import com.starwars.domain.entities.CharacterSearchModel
import com.starwars.domain.entities.Output

interface CharacterSearchRepo {

    suspend fun searchForCharacter(query: String?): Output<CharacterSearchModel?>
}