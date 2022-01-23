package dev.spght.owasp.home.data

import android.content.Context
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeyTemplates
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.spght.owasp.home.domain.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: RickAndMortyApi
): HomeRepository {

    override fun generateCrypto(): Aead {
        // Use Tink's strong cryptography to generate AES-256 algorithm for encryption
        return AndroidKeysetManager.Builder()
            .withSharedPref(context, "master_keyset", "master_key_preference")
            .withKeyTemplate(KeyTemplates.get("AES256_GCM"))
            .withMasterKeyUri("android-keystore://master_key")
            .build()
            .keysetHandle
            .getPrimitive(Aead::class.java)
    }

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