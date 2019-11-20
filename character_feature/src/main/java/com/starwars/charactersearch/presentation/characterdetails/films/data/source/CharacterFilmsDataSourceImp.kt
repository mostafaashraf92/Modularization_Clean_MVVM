package com.starwars.charactersearch.presentation.characterdetails.films.data.source

import com.starwars.domain.entities.FilmModel
import repositories.CharacterFilmDataSource
import retrofit2.Response

class CharacterFilmsDataSourceImp(private val characterFilmApi: CharacterFilmApi) :
    CharacterFilmDataSource<Response<FilmModel?>> {
    override suspend fun searchForFilms(query: String?): Response<FilmModel?> {
        return characterFilmApi.getDetailsAsync(query).await()
    }

}