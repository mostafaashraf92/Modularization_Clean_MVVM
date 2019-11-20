package com.starwars.charactersearch.presentation.charactersearch.data.source

import com.starwars.domain.entities.CharacterSearchModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterSearchApi {

    @GET("people/?")
    fun getCharactersAsync(@Query("search") query: String?)
            : Deferred<Response<CharacterSearchModel?>>
}