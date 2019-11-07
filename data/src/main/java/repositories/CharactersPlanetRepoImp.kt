package repositories

import com.starwars.domain.repository.CharacterPlanetRepo
import com.starwars.domain.entities.Output
import com.starwars.domain.entities.PlanetModel
import com.starwars.library.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CharactersPlanetRepoImp(var characterPlanetApi: com.starwars.data.datasource.CharacterPlanetApi) : BaseRepository(),
    CharacterPlanetRepo {
    override suspend fun search(url: String?): Output<PlanetModel?> {
        return withContext(Dispatchers.IO)
        {
            return@withContext safeApiCall(
                call = { characterPlanetApi.getDetailsAsync(url).await() }
            )
        }
    }

}