package com.starwars.charactersearch.presentation.characterdetails.planets.data.source

import com.starwars.domain.entities.PlanetModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface CharacterPlanetApi {

    @GET
    fun  getDetailsAsync(@Url url: String?)
            : Deferred<Response<PlanetModel?>>

}