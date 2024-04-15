package com.kreek.kreekandroid.common.manager.navigation.destination.patientchatroom

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.kreek.kreekandroid.common.manager.navigation.NavComposableDestination
import com.kreek.kreekandroid.common.manager.navigation.Route
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object ChatRoomNavDestination : NavComposableDestination {
    private const val name = "chatRoom"

    override val navComposableDestination =
        name.plus("/{${ChatRoomArguments.CHAT_ROOM_ID}}/{${ChatRoomArguments.CHAT_TYPE}}")

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(name = ChatRoomArguments.CHAT_ROOM_ID) {
            type = NavType.StringType
        },
        navArgument(name = ChatRoomArguments.CHAT_TYPE) {
            type = NavType.StringType
        },
    )

    open class ChatRoomArgumentsData(
        val chatRoomId: String,
        val chatType: String = "private"
    )

    fun parseArguments(arguments: Bundle?): ChatRoomArgumentsData =
        ChatRoomArgumentsData(
            chatRoomId = arguments?.getString(ChatRoomArguments.CHAT_ROOM_ID)
                ?.decodeUrl() ?: "",
            chatType = arguments?.getString(ChatRoomArguments.CHAT_TYPE)
                ?.decodeUrl() ?: "private",
        )


    fun getNavigationRoute(chatRoomId: String = "", chatType: String = "private") =
        object : Route {
            override val route = name
                .plus("/")
                .plus(chatRoomId.encodeUrl())
                .plus("/")
                .plus(chatType.encodeUrl())
        }


    private fun String.encodeUrl(): String =
        URLEncoder.encode(this, StandardCharsets.UTF_8.toString())

    private fun String.decodeUrl(): String =
        URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
}