package com.mine.cappella.screens

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mine.cappella.R

@Composable
fun RoundedImage(modifier: Modifier = Modifier, imageUrl: String) {

    AsyncImage(
        model = imageUrl ,
        contentDescription = stringResource(id = R.string.profile_image),
        placeholder = painterResource(id = R.drawable.mask),
        modifier = modifier
            .size(80.dp)
            .aspectRatio(1f)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}