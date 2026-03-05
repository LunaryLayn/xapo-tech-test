package com.hugopolog.demoappopen.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hugopolog.demoappopen.R

val HelveticaNow = FontFamily(

    Font(R.font.helveticanowtext_thin, FontWeight.Thin),
    Font(R.font.helveticanowtext_thinitalic, FontWeight.Thin),

    Font(R.font.helveticanowtext_extralight, FontWeight.ExtraLight),
    Font(R.font.helveticanowtext_extltita, FontWeight.ExtraLight),

    Font(R.font.helveticanowtext_light, FontWeight.Light),
    Font(R.font.helveticanowtext_lightitalic, FontWeight.Light),

    Font(R.font.helveticanowtext_regular, FontWeight.Normal),
    Font(R.font.helveticanowtext_regita, FontWeight.Normal),

    Font(R.font.helveticanowtext_medium, FontWeight.Medium),
    Font(R.font.helveticanowtext_mediumitalic, FontWeight.Medium),

    Font(R.font.helveticanowtext_bold, FontWeight.Bold),
    Font(R.font.helveticanowtext_bolditalic, FontWeight.Bold),

    Font(R.font.helveticanowtext_extrabold, FontWeight.ExtraBold),
    Font(R.font.helveticanowtext_extbdita, FontWeight.ExtraBold),

    Font(R.font.helveticanowtext_black, FontWeight.Black),
    Font(R.font.helveticanowtext_blackitalic, FontWeight.Black),
)

val AppTypography = Typography(

    displayLarge = TextStyle(
        fontFamily = HelveticaNow,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp
    ),

    headlineLarge = TextStyle(
        fontFamily = HelveticaNow,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),

    titleLarge = TextStyle(
        fontFamily = HelveticaNow,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    ),

    titleMedium = TextStyle(
        fontFamily = HelveticaNow,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = HelveticaNow,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = HelveticaNow,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

    labelLarge = TextStyle(
        fontFamily = HelveticaNow,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)