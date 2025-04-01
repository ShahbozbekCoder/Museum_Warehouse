package com.shahbozbek.museumwarehouse.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ArticlesItem(
    article: String,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = article,
            color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
        )
        HorizontalDivider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

data class Articles(
    val title: String
)

@Preview(showBackground = true)
@Composable
fun ArticlesItemPreview() {
    ArticlesItem(
        article = "Title"
    )
}