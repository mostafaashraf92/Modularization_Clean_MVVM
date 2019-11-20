package com.starwars.charactersearch.presentation.characterdetails.films.data.repo

import com.starwars.charactersearch.presentation.characterdetails.films.data.source.CharacterFilmsDataSourceImp
import com.starwars.domain.entities.FilmModel
import com.starwars.domain.entities.Output
import com.starwars.domain.repository.CharacterFilmsRepo
import com.starwars.library.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CharactersFilmsRepoImp(private var characterFilmsDataSourceImp: CharacterFilmsDataSourceImp) : BaseRepository(),
    CharacterFilmsRepo {
    override suspend fun search(url: String?): Output<FilmModel?> {

        return withContext(Dispatchers.IO)
        {
            return@withContext safeApiCall(
                call = { characterFilmsDataSourceImp.searchForFilms(url) }
            )
        }
    }

}