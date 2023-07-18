package com.example.fourchartest.di

import com.example.fourchartest.data.ApiFactory
import com.example.fourchartest.data.ApiService
import com.example.fourchartest.data.MovieRepositoryImpl
import com.example.fourchartest.domain.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object Module {

    @Singleton
    @Provides
    fun provideApiServices(): ApiService {
        return ApiFactory.apiService
    }

    @Singleton
    @Provides
    fun provideApiRepository(impl: ApiService): MovieRepository {
        return MovieRepositoryImpl(impl)
    }

}