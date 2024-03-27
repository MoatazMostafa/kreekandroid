package com.kreek.kreekandroid.common.manager.navigation

import com.kreek.kreekandroid.common.manager.navigation.destination.HomeNavDestination
import com.kreek.kreekandroid.common.manager.navigation.destination.SplashNavDestination

object MoviesNavDestination {

    val Splash = SplashNavDestination

    val Home = HomeNavDestination

   // val MovieDetails = MovieDetailsNavDestination
}



interface Route {
    val route: String
}
