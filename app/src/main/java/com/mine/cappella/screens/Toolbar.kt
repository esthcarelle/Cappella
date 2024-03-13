package com.mine.cappella.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mine.cappella.R
import com.mine.cappella.ui.theme.CappellaTheme
import com.mine.cappella.ui.theme.colorPrimary

@Composable
fun IconWithText(modifier :Modifier = Modifier, icon: Painter = painterResource(id = R.drawable.alt_arrow_downback), title: String, iconPadding: Dp = 16.dp, fontSize : TextUnit = 24.sp) {
    Row(modifier = modifier) {
        Icon(painter = icon, tint = colorPrimary, contentDescription = "Back Arrow", modifier = modifier.padding(iconPadding))
        Text(text = title, fontSize = fontSize,modifier = modifier.align(alignment = Alignment.CenterVertically),color = colorPrimary)
    }
}

@Preview(showBackground = true)
@Composable
fun ToolbarPreview() {
    CappellaTheme {
        IconWithText(
            title = "Baby's Profile"
        )
    }
}