package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 13.sp,
    alignment: TextAlign = TextAlign.Start,
    color: Color = Color.Black,
    fontFamily:FontFamily = FontFamily.SansSerif,
    fontWeight: FontWeight = FontWeight.Normal,
    maxLines:Int = Int.MAX_VALUE,
    onTextLayout:(TextLayoutResult)->Unit = {}
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        textAlign = alignment,
        color = color,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}