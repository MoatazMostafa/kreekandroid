package com.kreek.kreekandroid.ui.features.chatroom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.kreek.kreekandroid.ui.features.chatroom.composable.ChatRoomContent
import com.kreek.kreekandroid.ui.shared.base.BaseScreen

@Composable
fun ChatRoomScreen(
    modifier: Modifier = Modifier,
    patientChatRoomViewModel: ChatRoomViewModel
) {
    BaseScreen(content = {
        ChatRoomContent(
            modifier = modifier,
            chatRoomMessages = patientChatRoomViewModel.chatRoomMessages.collectAsState().value,
            userDoctor = patientChatRoomViewModel.userDoctor.collectAsState().value,
            onSendMessage = patientChatRoomViewModel::sendMessage,
            onPatientInfoClicked = patientChatRoomViewModel::onPatientInfoClicked
        )
    }, viewModel = patientChatRoomViewModel)
}