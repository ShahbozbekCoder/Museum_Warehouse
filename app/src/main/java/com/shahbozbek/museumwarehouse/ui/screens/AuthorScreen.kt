package com.shahbozbek.museumwarehouse.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shahbozbek.museumwarehouse.R

@Composable
fun AuthorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.loyiha_nomi),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Muzey ekspanatlarini invertirizatsiya jarayonini mobil ilovasini ishlab chiqish",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.loyiha_muallifi),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Abduxoliqova Aziza Abdulaziz qizi  317-21 DIoʻ",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.loyiha_muallifi_rahbari),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Raxmonova Nilufar Normurodovna-\n" +
                    "“Algoritmlash va matematik modellashtirish” kafedra assistenti",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AuthorScreenPreview() {
    AuthorScreen()
}