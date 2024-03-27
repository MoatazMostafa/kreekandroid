package com.kreek.kreekandroid.common.koin


import org.koin.dsl.module

/**
 * This property is used to create Repositories and inject needed parameters
 * */
val repositoryModule = module {

//    single<MoviesRepository> {
//        MoviesRepositoryImpl(
//            remoteMoviesDataSource = get(),
//            localDataSource = get()
//        )
//    }
//
//    single<MovieDetailsRepository> {
//        MovieDetailsRepositoryImpl(
//            remoteMovieDetailsDataSource = get(),
//            localDataSource = get()
//        )
//    }
}