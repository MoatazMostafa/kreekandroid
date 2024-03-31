package com.kreek.kreekandroid.ui.shared.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.R
import com.kreek.kreekandroid.ui.theme.BackgroundGray
import com.kreek.kreekandroid.ui.theme.DarkGreen
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme

@Composable
fun CircleShapeIcon(
    modifier: Modifier = Modifier,
    icon: Int = R.drawable.ic_stomach,
) {
    Surface(
        modifier = modifier.size(56.dp),
        shape = CircleShape,
        color = BackgroundGray,
    ) {
        Icon(
            modifier = Modifier.padding(8.dp),
            painter = painterResource(id = icon),
            contentDescription = "",
            tint = DarkGreen
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun CircleShapeButtonModePreview() {
    KreekandroidTheme {
        CircleShapeIcon()
    }
}
