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
import com.kreek.kreekandroid.ui.shared.uimodel.ChatData
import com.kreek.kreekandroid.ui.theme.TypographyCustom

@Composable
fun ChatDataItemContent(
    modifier: Modifier = Modifier,
    chatData: ChatData,
    onChatDataClick: (ChatData) -> Unit
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onChatDataClick(chatData) }
        .padding(vertical = 16.dp)) {
        if (chatData.patientUIModel == null) {
            PrivateItemContent(chatData)
        } else {
            GroupItemContent(chatData)
        }
    }
}

@Composable
fun PrivateItemContent(chatData: ChatData) {
    CircleShapeIcon(icon = R.drawable.ic_doctor)
    Spacer(modifier = Modifier.width(8.dp))
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = chatData.doctorUIModel?.name ?: "",
                style = TypographyCustom.headlineXSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = chatData.chatRoomInfoDomainModel.lastMessageTimestamp.toDateString(),
                style = TypographyCustom.bodyXSmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = chatData.chatRoomInfoDomainModel.lastMessage,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun GroupItemContent(chatData: ChatData) {
    CircleShapeIcon(icon = R.drawable.ic_lying_patient)
    Spacer(modifier = Modifier.width(8.dp))
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = chatData.patientUIModel?.patientData?.name ?: "",
                style = TypographyCustom.headlineXSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            if (chatData.chatRoomInfoDomainModel.lastMessageTimestamp > 0) {
                Text(
                    text = chatData.chatRoomInfoDomainModel.lastMessageTimestamp.toDateString(),
                    style = TypographyCustom.bodyXSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = chatData.chatRoomInfoDomainModel.lastMessage,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

