package dev.spght.owasp.home.data

import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): Response<RickAndMortyCharacterResponse>

}