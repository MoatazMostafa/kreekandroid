package com.kreek.kreekandroid.common.manager.navigation.destination.movieDetails

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.kreek.kreekandroid.common.manager.navigation.NavComposableDestination
import com.kreek.kreekandroid.common.manager.navigation.Route
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object MovieDetailsNavDestination : NavComposableDestination {
    private const val name = "movieDetails"

    override val navComposableDestination = name.plus("/{${MovieDetailsArguments.MOVIE_ID}}")

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(name = MovieDetailsArguments.MOVIE_ID) {
            type = NavType.StringType
        },
    )

    open class MovieDetailsArgumentsData(
        val movieId: String,
    )

    fun parseArguments(arguments: Bundle?): MovieDetailsArgumentsData =
        MovieDetailsArgumentsData(
            movieId = arguments?.getString(MovieDetailsArguments.MOVIE_ID)?.decodeUrl()?:""
        )


    fun getNavigationRoute(movieDetailsId: String = "") =
        object : Route {
            override val route = "$name/${movieDetailsId.encodeUrl()}"
        }

    fun String.encodeUrl(): String = URLEncoder.encode(this, StandardCharsets.UTF_8.toString())

    fun String.decodeUrl(): String = URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
}