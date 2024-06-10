package com.smwrd.picsum.view.compose

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.smwrd.picsum.R
import com.smwrd.picsum.domain.model.Photo

@Composable
fun PhotoList(photos: List<Photo>, modifier: Modifier = Modifier) {
    Column {
        LazyColumn(
            modifier = modifier
        ) {
            items(photos.size) { index ->
                PhotoItem(
                    photo = photos[index],
                    modifier = modifier
                )
            }
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PhotoItem(photo: Photo, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .size(80.dp)
                .clip(shape = CircleShape)
        ) {
            GlideImage(
                model = photo.url,
                loading = placeholder(R.mipmap.ic_launcher),
                contentDescription = photo.id.toString(),
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("P")
                }
                withStyle(
                    SpanStyle(
                        brush = Brush.linearGradient(listOf(Color.DarkGray, Color.Gray)),
                        alpha = .8f
                    )
                ) {
                    append("ic")
                }

                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                    append("S")
                }
                withStyle(
                    SpanStyle(
                        brush = Brush.linearGradient(listOf(Color.Gray, Color.DarkGray)),
                        alpha = .5f
                    )
                ) {
                    append("um")
                }
            },
            modifier = if(photo.id == 1) {
                val infiniteTransition = rememberInfiniteTransition()
                val rotation by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 360f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(3000, easing = LinearEasing),
                        repeatMode = RepeatMode.Restart
                    ), label = "rotate"
                )
                Modifier.rotate(rotation)
            }
            else
                Modifier
        )
        Text(
            text = photo.id.toString(),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.ExtraBold,
                shadow = Shadow(
                    color = Color.Red,
                    offset = Offset(3f, 3f),
                    blurRadius = 10f
                ),
                brush = Brush.linearGradient(
                    colors = listOf(Color.Red, Color.Blue),
                )
            ),
            modifier = Modifier
                .padding(start = 10.dp)
        )
        Text(
            text = photo.author,
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily.Monospace,
            ),
            modifier = Modifier
                .padding(start = 10.dp)
        )
        Text(
            text = "(${photo.width}x${photo.height})",
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily.Monospace,
            ),
            modifier = Modifier
                .padding(start = 3.dp)
        )
    }
}

@Preview(
    name = "PhotoList",
    group = "Photo",
    showBackground = true)
@Composable
fun Preview1() {
    PhotoList(photos = listOf(
        Photo(
            id = 1,
            author = "smwrd",
            width = 1080,
            height = 1920,
            url = "https://picsum.photos/id/1/1080/1920"
        ),
        Photo(
            id = 2,
            author = "smwrd",
            width = 1080,
            height = 1920,
            url = "https://picsum.photos/id/2/1080/1920"
        )
    ))
}

@Preview(
    name = "PhotoList",
    group = "Photo",
    showBackground = true,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE)
@Composable
fun Preview2() {
    Preview1()
}