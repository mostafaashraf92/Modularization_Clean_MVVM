package repositories

import com.starwars.library.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CharactersFilmsRepoImp(var characterFilmApi: com.starwars.data.datasource.CharacterFilmApi) : BaseRepository(),
    com.starwars.domain.repository.CharacterFilmsRepo {
    override suspend fun search(url: String?): com.starwars.domain.entities.Output<com.starwars.domain.entities.FilmModel?> {

        return withContext(Dispatchers.IO)
        {
            return@withContext safeApiCall(
                call = { characterFilmApi.getDetailsAsync(url).await() }
            )
        }
    }

}