package dev.spght.owasp.home.domain

import com.google.crypto.tink.Aead
import dev.spght.owasp.home.data.RickAndMortyCharacter

interface HomeRepository {
    fun generateCrypto(): Aead
    suspend fun fetchCharacters(): List<RickAndMortyCharacter>
}