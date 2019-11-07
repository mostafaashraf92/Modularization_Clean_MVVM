package com.starwars.data.datasource

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterFilmApi {

    @GET
    fun getDetailsAsync(@Url url: String?)
            : Deferred<Response<com.starwars.domain.entities.FilmModel?>>


}