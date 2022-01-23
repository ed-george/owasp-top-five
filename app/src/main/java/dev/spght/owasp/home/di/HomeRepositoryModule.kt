package dev.spght.owasp.home.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.spght.owasp.home.data.HomeRepositoryImpl
import dev.spght.owasp.home.domain.HomeRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeRepositoryModule {
    @Binds
    abstract fun bindsHomeRepository(impl: HomeRepositoryImpl): HomeRepository
}