package dev.spght.owasp.login.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.spght.owasp.login.data.LoginRepositoryImpl
import dev.spght.owasp.login.domain.LoginRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginRepositoryModule {

    @Singleton
    @Provides
    fun provideLoginRepository(sharedPreferences: SharedPreferences): LoginRepository {
        return LoginRepositoryImpl(sharedPreferences)
    }

    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            "my_secure_app_prefs",
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    }

}