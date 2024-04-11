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
import com.kreek.kreekandroid.ui.features.chatroom.model.ChatMessageUIModel
import com.kreek.kreekandroid.ui.features.chatroom.model.ChatRoomType
import com.kreek.kreekandroid.ui.shared.composables.CircleShapeButton
import com.kreek.kreekandroid.ui.shared.composables.CircleShapeIcon
import com.kreek.kreekandroid.ui.shared.uimodel.PatientUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.getIconResource
import com.kreek.kreekandroid.ui.shared.uimodel.getMockPatient
import com.kreek.kreekandroid.ui.theme.Black
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import com.kreek.kreekandroid.ui.theme.TypographyCustom
import com.kreek.kreekandroid.ui.theme.White

@Composable
fun PatientChatRoomContent(
    modifier: Modifier = Modifier,
    patient: PatientUIModel,
    userId: String,
    chatRoomType: ChatRoomType,
    messages: List<ChatMessageUIModel>,
    onSendMessage: (String) -> Unit,
    onPatientInfoClicked: () -> Unit,
) {

    Column(Modifier.fillMaxSize()) {
        Row(modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clickable { onPatientInfoClicked() }) {
            CircleShapeIcon(
                icon = if (chatRoomType == ChatRoomType.CHAT_BOT) {
                    R.drawable.ic_chat_bot
                } else {
                    patient.patientDiagnosis.getIconResource()
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (chatRoomType == ChatRoomType.CHAT_BOT) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = "Chat with Kreek AI",
                    style = TypographyCustom.headlineXSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            } else {
                Column(modifier = Modifier.padding(top = 8.dp)) {

                    Text(
                        text = patient.name,
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
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .weight(1f)
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .padding(top = 16.dp)
        ) { // Content area
            messages.forEach {
                if (it.senderId == userId) {
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
        PatientChatRoomContent(
            patient = getMockPatient(),
            userId = "1",
            messages = listOf(
                ChatMessageUIModel(
                    "1",
                    "",
                    "1",
                    "1",
                    "1",
                    "indications of chest tube",
                    1,
                ),
                ChatMessageUIModel(
                    "2",
                    "",
                    "1",
                    "1",
                    "1",
                    "Chest tube insertion is indicated in various situations such as tension pneumothorax, hemothorax, and empyema. In tension pneumothorax, chest tube insertion is mandatory following needle decompression [7]. Massive traumatic hemothorax requires chest tube placement to decompress the chest cavity and restore blood volume [9]. In cases of empyema, treatment involves antibiotic medication combined with early chest tube placement and instillation of fibrinolytics or early video-assisted thoracoscopic surgery [10]. Proper placement of chest tubes is crucial to avoid complications, and they should be inserted in specific locations to prevent injury to underlying structures [5]. It is essential to involve a pediatric or thoracic surgeon early in the management of these conditions [10]",
                    1,
                )
            ),
            onSendMessage = {},
            onPatientInfoClicked = {},
            chatRoomType = ChatRoomType.CHAT_BOT
        )
    }
}