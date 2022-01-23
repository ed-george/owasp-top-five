package dev.spght.owasp.home.data

import dev.spght.owasp.home.domain.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
): HomeRepository {
    override suspend fun fetchCharacters(): List<RickAndMortyCharacter> {
        // Don't do networking like this IRL :)
        try {
            val response = api.getCharacters()
            if (response.isSuccessful) {
                return response.body()?.results.orEmpty()
            }
        } catch (t: Throwable) {
            t.printStackTrace()
        }
        return emptyList()
    }
}