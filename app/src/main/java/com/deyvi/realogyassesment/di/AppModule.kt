package com.deyvi.realogyassesment.di

import com.deyvi.realogyassesment.BuildConfig
import com.deyvi.realogyassesment.data.remote.CharactersApi
import com.deyvi.realogyassesment.data.repository.CharactersRepositoryImpl
import com.deyvi.realogyassesment.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCharactersApi(): CharactersApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharactersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(api: CharactersApi): CharactersRepository {
        return CharactersRepositoryImpl(api = api)
    }
}