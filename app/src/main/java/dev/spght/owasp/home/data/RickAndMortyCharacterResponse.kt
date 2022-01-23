package dev.spght.owasp.home.data

import com.squareup.moshi.Json

data class RickAndMortyCharacterResponse(
    @field:Json(name = "results") val results: List<RickAndMortyCharacter>?
)