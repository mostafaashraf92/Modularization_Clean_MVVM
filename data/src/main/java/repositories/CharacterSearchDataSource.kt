package repositories

interface CharacterSearchDataSource<T> {
    suspend fun searchForCharacter(query: String?): T?

}