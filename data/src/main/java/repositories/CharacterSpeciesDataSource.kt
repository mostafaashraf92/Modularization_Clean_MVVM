package repositories

interface CharacterSpeciesDataSource<T> {
    suspend fun searchForSpecies(query: String?): T?

}