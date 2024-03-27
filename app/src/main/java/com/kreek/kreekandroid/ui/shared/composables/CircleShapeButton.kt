package com.kreek.kreekandroid.ui.shared.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.R
import com.kreek.kreekandroid.ui.theme.Black
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import com.kreek.kreekandroid.ui.theme.LightGray
import com.kreek.kreekandroid.ui.theme.White

@Composable
fun CircleShapeButton(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.ic_search,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier.size(56.dp),
        onClick = { onClick() }) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            shape = CircleShape,
            color = White,
            border = BorderStroke(width = 1.dp, color = LightGray)

        ) {
            Icon(
                modifier = Modifier.padding(8.dp),
                painter = painterResource(id = icon),
                contentDescription = "",
                tint = Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CircleShapeButtonModePreview() {
    KreekandroidTheme {
        CircleShapeButton {}
    }
}
