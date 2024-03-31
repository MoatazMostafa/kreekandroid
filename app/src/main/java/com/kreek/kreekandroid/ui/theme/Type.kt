package com.kreek.kreekandroid.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kreek.kreekandroid.R

val inter = FontFamily(
    Font(R.font.inter_regular),
    Font(R.font.inter_medium, FontWeight.W500),
    Font(R.font.inter_semibold, FontWeight.W600),
    Font(R.font.inter_bold, FontWeight.Bold),
    Font(R.font.inter_extrabold, FontWeight.ExtraBold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight(600),
        fontSize = 28.sp,
        lineHeight = 39.2.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight(600),
        fontSize = 22.sp,
        lineHeight = 31.5.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight(600),
        fontSize = 18.sp,
        lineHeight = 25.2.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight(500),
        fontSize = 16.sp,
        lineHeight = 22.4.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight(500),
        fontSize = 14.4.sp,
        lineHeight = 20.16.sp,
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

object TypographyCustom {
    val headlineXSmall = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight(600),
        fontSize = 14.4.sp,
        lineHeight = 20.16.sp
    )
    val bodyXSmall = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight(500),
        fontSize = 12.sp,
        lineHeight = 14.52.sp,
    )
    val bodyRegular = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight(400),
        fontSize = 18.sp,
        lineHeight = 25.2.sp,
    )
}