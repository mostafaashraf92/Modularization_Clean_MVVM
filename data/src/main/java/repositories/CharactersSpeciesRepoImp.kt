package repositories

import com.starwars.library.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CharactersSpeciesRepoImp(var characterDetailsApi: com.starwars.data.datasource.CharacterSpeciesApi) : BaseRepository(),
    com.starwars.domain.repository.CharacterSpeciesRepo {
    override suspend fun search(url: String?): com.starwars.domain.entities.Output<com.starwars.domain.entities.SpeciesModel?> {

        return withContext(Dispatchers.IO)
        {
            return@withContext safeApiCall(
                call = { characterDetailsApi.getDetailsAsync(url).await() }
            )
        }
    }

}