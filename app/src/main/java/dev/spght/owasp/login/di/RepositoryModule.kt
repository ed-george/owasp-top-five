package dev.spght.owasp.login.di

import android.content.Context
import android.content.SharedPreferences
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
object RepositoryModule {

    @Singleton
    @Provides
    fun provideLoginRepository(sharedPreferences: SharedPreferences): LoginRepository {
        return LoginRepositoryImpl(sharedPreferences)
    }

    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("my_secure_app_prefs", Context.MODE_PRIVATE)
    }

}