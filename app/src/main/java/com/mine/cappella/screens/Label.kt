package com.mine.cappella.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mine.cappella.ui.theme.colorTertiary
import com.mine.cappella.ui.theme.poppinsFamily

@Composable
fun Label(modifier: Modifier = Modifier, text: String, isCompulsory: Boolean = false) {
    var modifiedText = text

    if (isCompulsory)
        modifiedText += " *"
    Text(
        text = modifiedText,
        fontSize = 12.sp,
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal,
        color = colorTertiary
    )
}