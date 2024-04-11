package com.kreek.kreekandroid.common.manager.navigation.destination.patientinfo

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.kreek.kreekandroid.common.manager.navigation.NavComposableDestination
import com.kreek.kreekandroid.common.manager.navigation.Route
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object PatientInfoNavDestination : NavComposableDestination {
    private const val name = "patientInfo"

    override val navComposableDestination =
        name.plus("/{${PatientInfoArguments.PATIENT_ID}}")

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(name = PatientInfoArguments.PATIENT_ID) {
            type = NavType.StringType
        },
    )

    open class PatientInfoArgumentsData(
        val patientId: String,
    )

    fun parseArguments(arguments: Bundle?): PatientInfoArgumentsData =
        PatientInfoArgumentsData(
            patientId = arguments?.getString(PatientInfoArguments.PATIENT_ID)
                ?.decodeUrl() ?: ""
        )


    fun getNavigationRoute(patientId: String = "") =
        object : Route {
            override val route = "$name/${patientId.encodeUrl()}"
        }

    private fun String.encodeUrl(): String =
        URLEncoder.encode(this, StandardCharsets.UTF_8.toString())

    private fun String.decodeUrl(): String =
        URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
}
