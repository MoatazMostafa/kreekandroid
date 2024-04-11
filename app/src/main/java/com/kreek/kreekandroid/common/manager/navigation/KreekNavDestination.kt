package com.kreek.kreekandroid.common.manager.navigation

import com.kreek.kreekandroid.common.manager.navigation.destination.HomeNavDestination
import com.kreek.kreekandroid.common.manager.navigation.destination.SplashNavDestination
import com.kreek.kreekandroid.common.manager.navigation.destination.patientchatroom.ChatRoomNavDestination
import com.kreek.kreekandroid.common.manager.navigation.destination.patientinfo.PatientInfoNavDestination

object KreekNavDestination {

    val Splash = SplashNavDestination

    val Home = HomeNavDestination

    val ChatRoom = ChatRoomNavDestination

    val PatientInfo = PatientInfoNavDestination

}



interface Route {
    val route: String
}
