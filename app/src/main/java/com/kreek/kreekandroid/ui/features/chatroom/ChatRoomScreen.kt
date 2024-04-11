package com.kreek.kreekandroid.ui.features.chatroom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.kreek.kreekandroid.ui.features.chatroom.composable.PatientChatRoomContent
import com.kreek.kreekandroid.ui.shared.base.BaseScreen

@Composable
fun ChatRoomScreen(
    modifier: Modifier = Modifier,
    patientChatRoomViewModel: ChatRoomViewModel
) {
    BaseScreen(content = {
        PatientChatRoomContent(
            modifier = modifier,
            patient = patientChatRoomViewModel.patient.collectAsState().value,
            userId = patientChatRoomViewModel.userId,
            chatRoomType = patientChatRoomViewModel.chatRoomType,
            messages = patientChatRoomViewModel.chatMessages.collectAsState().value,
            onSendMessage = patientChatRoomViewModel::sendMessage,
            onPatientInfoClicked = patientChatRoomViewModel::onPatientInfoClicked
        )
    }, viewModel = patientChatRoomViewModel)
}