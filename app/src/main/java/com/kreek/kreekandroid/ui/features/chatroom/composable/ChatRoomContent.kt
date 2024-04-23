package com.kreek.kreekandroid.ui.features.chatroom.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.R
import com.kreek.kreekandroid.data.firebase.chat.model.ChatType
import com.kreek.kreekandroid.ui.shared.composables.CircleShapeButton
import com.kreek.kreekandroid.ui.shared.composables.CircleShapeIcon
import com.kreek.kreekandroid.ui.shared.uimodel.ChatRoomMessagesUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.DoctorUIModel
import com.kreek.kreekandroid.ui.theme.Black
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import com.kreek.kreekandroid.ui.theme.TypographyCustom
import com.kreek.kreekandroid.ui.theme.White

@Composable
fun ChatRoomContent(
    modifier: Modifier = Modifier,
    chatRoomMessages: ChatRoomMessagesUIModel,
    userDoctor: DoctorUIModel,
    onSendMessage: (String) -> Unit,
    onPatientInfoClicked: () -> Unit,
) {

    Column(Modifier.fillMaxSize()) {
        Row(modifier = modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clickable { onPatientInfoClicked() }) {
            CircleShapeIcon(
                icon = when (chatRoomMessages.chatType) {
                    ChatType.VECTARA_CHAT_BOT -> {
                        R.drawable.ic_chat_bot
                    }

                    ChatType.PRIVATE -> {
                        R.drawable.ic_doctor
                    }

                    ChatType.GROUP -> {
                        R.drawable.ic_lying_patient
                    }
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            when (chatRoomMessages.chatType) {
                ChatType.VECTARA_CHAT_BOT -> {
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Chat with Kreek AI",
                        style = TypographyCustom.headlineXSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                ChatType.PRIVATE -> {
                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(
                            text = if (userDoctor.id == chatRoomMessages.firstUserId)
                                chatRoomMessages.secondUserName
                            else
                                chatRoomMessages.firstUserName,
                            style = TypographyCustom.headlineXSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (userDoctor.id == chatRoomMessages.firstUserId)
                                chatRoomMessages.secondUserSpeciality
                            else
                                chatRoomMessages.firstUserSpeciality,
                            style = TypographyCustom.bodyXSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                ChatType.GROUP -> {
                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(
                            text = chatRoomMessages.patientName,
                            style = TypographyCustom.headlineXSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(R.string.tap_here_for_more_info),
                            style = TypographyCustom.bodyXSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .weight(1f)
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(top = 16.dp)
        ) {
            chatRoomMessages.chatMessageList.forEach {
                if (it.senderId == userDoctor.id) {
                    Box(
                        Modifier
                            .padding(start = 52.dp, end = 16.dp)
                            .align(Alignment.End)
                            .clip(shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 16.dp))
                            .background(MaterialTheme.colorScheme.tertiary),
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            style = TypographyCustom.bodyRegular,
                            color = Black,
                            text = it.message
                        )
                    }
                } else {
                    Box(
                        Modifier
                            .padding(end = 52.dp, start = 16.dp)
                            .align(Alignment.Start)
                            .clip(shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 0.dp))
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            style = TypographyCustom.bodyRegular,
                            color = Black,
                            text = it.message
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Box {
            Row(
                Modifier
                    .height(90.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
                    .align(Alignment.BottomCenter),
                verticalAlignment = Alignment.CenterVertically
            ) {

                val text = remember { mutableStateOf(TextFieldValue("")) }

                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = text.value,
                    onValueChange = { text.value = it },
                    shape = RoundedCornerShape(34.dp),
                    label = { Text("") },
                    maxLines = 1,
                    colors = OutlinedTextFieldDefaults.colors().copy(
                        focusedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                    )
                )
                val circleShapeButton = if (text.value.text.isNotBlank()) {
                    R.drawable.ic_send_message
                } else {
                    R.drawable.ic_mic
                }
                Spacer(modifier = Modifier.width(16.dp))
                CircleShapeButton(
                    modifier = Modifier.fillMaxHeight(),
                    icon = circleShapeButton,
                    color = MaterialTheme.colorScheme.secondary,
                    borderColor = Color.Transparent,
                    tint = White,
                    onClick = {
                        if (text.value.text.isNotBlank()) {
                            onSendMessage(text.value.text)
                            text.value = TextFieldValue("")
                        }
                    })
            }
        }
    }
}

@Preview
@Composable
fun PatientChatRoomContentPreview() {
    KreekandroidTheme {
        ChatRoomContent(
            modifier = Modifier,
            chatRoomMessages = ChatRoomMessagesUIModel(),
            userDoctor = DoctorUIModel(),
            onSendMessage = { },
            onPatientInfoClicked = { },
        )
    }
}