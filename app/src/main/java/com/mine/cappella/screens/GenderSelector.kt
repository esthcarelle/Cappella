package com.mine.cappella.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mine.cappella.R
import com.mine.cappella.ui.theme.colorPrimary
import com.mine.cappella.ui.theme.colorSecondary
import com.mine.cappella.ui.theme.poppinsFamily

@Composable
fun GenderSelector(modifier: Modifier = Modifier, initialText: String = "Select",onValueChange: (String) -> Unit = {}) {
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Male", "Female")
    var borderColor by remember {
        mutableStateOf(colorPrimary)
    }
    borderColor = if (expanded)
        colorPrimary
    else
        colorSecondary

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(Color.White)
            .clickable { expanded = !expanded }
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(10.dp)
            )
            .height(56.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = initialText,
            modifier = modifier
                .padding(16.dp)
                .align(Alignment.CenterVertically),
            color = colorPrimary,
            fontSize = 16.sp,
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Normal,

            )
        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(16.dp),
            painter = painterResource(id = R.drawable.alt_arrow_down),
            tint = colorPrimary,
            contentDescription = "Arrow Icon"
        )
    }
    DropdownMenu(
        expanded = expanded,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(
                width = 1.dp,
                color = colorPrimary,
                shape = RoundedCornerShape(10.dp)
            ),
        onDismissRequest = { expanded = false },
    ) {
        suggestions.forEachIndexed { i, label ->
            DropdownMenuItem(text = {
                Column {
                    Text(
                        modifier = modifier.fillMaxWidth(),
                        text = label, color = colorPrimary,
                        fontSize = 16.sp,
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Normal
                    )
                    if (i != suggestions.size - 1) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(16.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(colorSecondary)
                                .height(1.dp)
                        )
                    }
                }

            }, onClick = {
                expanded = false
                onValueChange.invoke(label)
            })
        }
    }
}