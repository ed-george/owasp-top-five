package dev.spght.owasp.home.domain

import dev.spght.owasp.home.data.RickAndMortyCharacter

interface HomeRepository {
    suspend fun fetchCharacters(): List<RickAndMortyCharacter>
}