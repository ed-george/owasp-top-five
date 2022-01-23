package dev.spght.owasp.home.data

import com.squareup.moshi.Json

data class RickAndMortyCharacter(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "species") val species: String
)