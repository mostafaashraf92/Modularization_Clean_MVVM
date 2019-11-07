package com.starwars.domain.usecase

import com.starwars.domain.entities.FilmModel
import com.starwars.domain.entities.Output
import com.starwars.domain.repository.CharacterFilmsRepo

class GetFilmsUseCase(private val charactersFilmsRepoImp: CharacterFilmsRepo) :
    UseCase<FilmModel>() {

    suspend fun search(url: String?): Output<FilmModel?> {
        return charactersFilmsRepoImp.search(url)
    }
}