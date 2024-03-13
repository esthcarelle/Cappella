package com.mine.cappella.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mine.cappella.R
import com.mine.cappella.ui.theme.CappellaTheme
import com.mine.cappella.ui.theme.colorSecondary

@Composable
fun RegistrationScreen(modifier: Modifier = Modifier) {
    Column {
        IconWithText(title = "Baby's profile")
        Surface(
            modifier = modifier
                .background(Color.White)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp), shape = RoundedCornerShape(24.dp)
        ) {

            Column(modifier = modifier.padding(16.dp)) {
                RoundedImage(modifier = Modifier.align(Alignment.CenterHorizontally))
                IconWithText(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 16.dp),
                    icon = painterResource(id = R.drawable.vectoredit_pen),
                    title = "Change profile picture",
                    fontSize = 14.sp,
                    iconPadding = 2.dp
                )

                Divider(
                    color = colorSecondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .width(2.dp)
                )
            }
        }
    }

}

@Composable
fun Label(text: String) {

}

@Composable
fun RoundedImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.ic_launcher_background),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,            // crop the image if it's not a square
        modifier = modifier
            .size(64.dp)
            .clip(CircleShape)                       // clip to the circle shape
    )
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    CappellaTheme {
        RegistrationScreen()
    }
}