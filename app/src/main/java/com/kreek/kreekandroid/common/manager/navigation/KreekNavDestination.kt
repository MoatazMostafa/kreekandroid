package com.kreek.kreekandroid.common.manager.navigation

import com.kreek.kreekandroid.common.manager.navigation.destination.AuthenticationNavDestination
import com.kreek.kreekandroid.common.manager.navigation.destination.HomeNavDestination
import com.kreek.kreekandroid.common.manager.navigation.destination.SelectDoctorChatNavDestination
import com.kreek.kreekandroid.common.manager.navigation.destination.SplashNavDestination
import com.kreek.kreekandroid.common.manager.navigation.destination.patientchatroom.ChatRoomNavDestination
import com.kreek.kreekandroid.common.manager.navigation.destination.patientinfo.PatientInfoNavDestination

object KreekNavDestination {

    val Splash = SplashNavDestination

    val Home = HomeNavDestination

    val ChatRoom = ChatRoomNavDestination

    val PatientInfo = PatientInfoNavDestination

    val SelectDoctorChat = SelectDoctorChatNavDestination

    val Authentication = AuthenticationNavDestination
}



interface Route {
    val route: String
}
