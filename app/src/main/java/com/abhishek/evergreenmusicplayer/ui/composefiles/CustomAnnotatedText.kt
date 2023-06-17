package com.abhishek.evergreenmusicplayer.ui.composefiles

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun CustomAnnotatedText(
    annotatedText: AnnotatedString,
    fontSize: TextUnit,
    alignment: TextAlign,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = annotatedText,
        fontSize = fontSize,
        textAlign = alignment
    )
}