package com.kreek.kreekandroid.ui.features.authentication.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kreek.kreekandroid.R
import com.kreek.kreekandroid.ui.shared.composables.CustomEditText
import com.kreek.kreekandroid.ui.shared.util.Constants.MOBILE_NUMBER_LENGTH
import com.kreek.kreekandroid.ui.theme.KreekandroidTheme

@Composable
fun VerifyNumberContent(
    modifier: Modifier = Modifier,
    onVerifyNumber: (String) -> Unit,
) {
    val numberText = remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(52.dp))
        Image(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_kreek_circler_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            modifier = Modifier,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            text = stringResource(R.string.verify_number_welcome)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            text = stringResource(R.string.verify_number_start)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            text = stringResource(R.string.verify_number_mobile_number)
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomEditText(
            hint = stringResource(R.string.verify_number_enter_your_mobile_number),
            text = numberText,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(modifier = Modifier
            .height(52.dp)
            .fillMaxWidth(),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            ),
            onClick = {
                if (numberText.value.length >= MOBILE_NUMBER_LENGTH) {
                    onVerifyNumber(numberText.value)
                }
            }) {
            Text(
                modifier = Modifier,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary,
                text = stringResource(R.string.verify_number_login_button)
            )
        }

    }
}

@Preview
@Composable
fun VerifyNumberContentPreview() {
    KreekandroidTheme {
        VerifyNumberContent(onVerifyNumber = {})
    }
}