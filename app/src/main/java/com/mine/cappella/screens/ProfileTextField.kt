package com.mine.cappella.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mine.cappella.ui.theme.LocalSpacing
import com.mine.cappella.ui.theme.colorSecondary
import com.mine.cappella.ui.theme.onTextColor

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileTextField(
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    placeholderTextStyle: androidx.compose.ui.text.TextStyle = androidx.compose.ui.text.TextStyle(
        color = onTextColor
    )
) {
    val spacing = LocalSpacing.current

    TextField(
        value = value,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .border(
                width = 1.dp,
                color = colorSecondary,
                shape = RoundedCornerShape(10.dp)
            ),
        onValueChange = onValueChange,
        singleLine = true,
        shape = RoundedCornerShape(spacing.spaceExtraSmall),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedPlaceholderColor = Color.Gray,
            focusedTextColor = onTextColor,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = placeholder,
                style = placeholderTextStyle,
                fontSize = 16.sp
            )
        })
}