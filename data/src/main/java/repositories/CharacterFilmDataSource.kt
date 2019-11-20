package repositories

interface CharacterFilmDataSource<T> {
    suspend fun searchForFilms(query: String?): T?

}