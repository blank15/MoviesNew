package com.blank.movie.di

import com.blank.movie.domain.repository.MovieRepository
import com.blank.movie.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}