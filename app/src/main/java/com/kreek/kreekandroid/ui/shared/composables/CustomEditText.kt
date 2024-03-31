package com.kreek.kreekandroid.ui.shared.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.ui.theme.BackgroundGray
import com.kreek.kreekandroid.ui.theme.Gray
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme
import com.kreek.kreekandroid.ui.theme.LightGray
import com.kreek.kreekandroid.ui.theme.TypographyCustom

@Composable
fun CustomEditText(
    modifier: Modifier = Modifier,
    hint: String = "",
    text: MutableState<String> = remember { mutableStateOf("") },
) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        value = text.value,
        onValueChange = { text.value = it },
        placeholder = { Text(text = hint, style = TypographyCustom.bodyRegular, color = Gray) },
        textStyle = TypographyCustom.bodyRegular,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = BackgroundGray,
            focusedContainerColor = BackgroundGray,
            disabledContainerColor = LightGray,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )
    )
}

@Preview
@Composable
fun CustomEditTextPreview() {
    KreekandroidTheme {
        CustomEditText(hint = "Enter your name")
    }
}