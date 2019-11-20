package com.starwars.charactersearch.presentation.characterdetails.species.data.source

import com.starwars.domain.entities.SpeciesModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterSpeciesApi {

    @GET
    fun  getDetailsAsync(@Url url: String?)
            : Deferred<Response<SpeciesModel?>>


}