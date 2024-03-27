package com.kreek.kreekandroid.ui.features.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme

@Composable
fun PatientListItem(
    name: String,
    date: String,
    description: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = date)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = description)
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun PatientListItemModePreview() {
    KreekandroidTheme {
        PatientListItem(
            name = "Anas Omran",
            date = "31/01/2024",
            description = "Moamen: This patient needs more care and has a critical case to be handled by ..."
        )
    }
}