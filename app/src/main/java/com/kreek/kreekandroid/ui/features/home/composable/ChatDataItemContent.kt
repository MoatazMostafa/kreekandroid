package com.kreek.kreekandroid.ui.features.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.R
import com.kreek.kreekandroid.common.util.toDateString
import com.kreek.kreekandroid.ui.shared.composables.CircleShapeIcon
import com.kreek.kreekandroid.ui.shared.uimodel.ChatRoomMessagesUIModel
import com.kreek.kreekandroid.ui.shared.uimodel.DoctorUIModel
import com.kreek.kreekandroid.ui.theme.TypographyCustom

@Composable
fun ChatDataItemContent(
    modifier: Modifier = Modifier,
    chatData: ChatRoomMessagesUIModel,
    userDoctor: DoctorUIModel,
    onChatDataClick: (ChatRoomMessagesUIModel) -> Unit
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onChatDataClick(chatData) }
        .padding(vertical = 16.dp)) {
        if (chatData.patientId.isBlank()) {
            PrivateItemContent(chatData, userDoctor)
        } else {
            GroupItemContent(chatData, userDoctor)
        }
    }
}

@Composable
fun PrivateItemContent(
    chatData: ChatRoomMessagesUIModel,
    userDoctor: DoctorUIModel
) {
    CircleShapeIcon(icon = R.drawable.ic_doctor)
    Spacer(modifier = Modifier.width(8.dp))
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (userDoctor.id == chatData.firstUserId)
                    chatData.secondUserName
                else
                    chatData.firstUserName,
                style = TypographyCustom.headlineXSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = chatData.lastMessageTimestamp.toDateString(),
                style = TypographyCustom.bodyXSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = chatData.lastMessage ?: "",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun GroupItemContent(
    chatData: ChatRoomMessagesUIModel,
    userDoctor: DoctorUIModel
) {
    CircleShapeIcon(icon = R.drawable.ic_lying_patient)
    Spacer(modifier = Modifier.width(8.dp))
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = chatData.patientName,
                style = TypographyCustom.headlineXSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = chatData.lastMessageTimestamp.toDateString(),
                style = TypographyCustom.bodyXSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = chatData.lastMessage?:"",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

