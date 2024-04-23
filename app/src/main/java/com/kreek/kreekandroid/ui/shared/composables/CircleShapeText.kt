package com.kreek.kreekandroid.ui.shared.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.ui.theme.Primary
import com.kreek.kreekandroid.ui.theme.TypographyCustom
import com.kreek.kreekandroid.ui.theme.White

@Composable
fun CircleShapeText(
    text: String = "",
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.background(Primary, CircleShape)
    ) {
        Text(
            modifier = Modifier.padding(2.dp),
            text = text,
            color = White,
            style = TypographyCustom.bodyXSmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}