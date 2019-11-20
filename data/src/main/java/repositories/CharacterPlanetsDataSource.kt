package repositories

interface CharacterPlanetsDataSource<T> {
    suspend fun searchForPlanets(query: String?): T?

}