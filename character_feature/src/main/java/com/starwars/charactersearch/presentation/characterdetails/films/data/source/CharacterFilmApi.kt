package com.starwars.charactersearch.presentation.characterdetails.films.data.source

import com.starwars.domain.entities.FilmModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterFilmApi {

    @GET
    fun getDetailsAsync(@Url url: String?)
            : Deferred<Response<FilmModel?>>


}