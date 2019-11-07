package com.starwars.domain.repository

import com.starwars.domain.entities.FilmModel
import com.starwars.domain.entities.Output

interface CharacterFilmsRepo {

    suspend fun search(url: String?): Output<FilmModel?>
}