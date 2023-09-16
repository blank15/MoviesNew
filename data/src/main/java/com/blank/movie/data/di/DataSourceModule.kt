package com.blank.movie.data.di

import com.blank.movie.data.MovieDataSource
import com.blank.movie.data.network.MovieDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindMovieDataSource(movieDataSourceImpl: MovieDataSourceImpl): MovieDataSource
}
