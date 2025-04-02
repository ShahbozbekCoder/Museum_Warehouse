package com.shahbozbek.museumwarehouse.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahbozbek.museumwarehouse.R
import com.shahbozbek.museumwarehouse.data.ArticlesData

@Composable
fun ViewArticleScreen(
    article: ArticlesData,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = article.title,
                fontSize = 24.sp,
                color = Color.Blue,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = article.content,
                fontSize = 16.sp,
                color = if (isSystemInDarkTheme()) Color.LightGray else Color.Black,
                fontFamily = FontFamily.Serif,
                softWrap = true
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Image(
                painter = painterResource(id = article.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewArticleScreenPreview() {
    ViewArticleScreen(
        article = ArticlesData(
            title = "Article Title",
            content = "Article Content",
            image = R.drawable.tv_tower
        ),
        paddingValues = PaddingValues(0.dp)
    )
}