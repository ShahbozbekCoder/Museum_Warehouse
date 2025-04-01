package com.shahbozbek.museumwarehouse.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shahbozbek.museumwarehouse.R

@Composable
fun LanguageScreen(
    mainScreenViewModel: MainScreenViewModel = hiltViewModel<MainScreenViewModel>(),
    navController: NavController,
    onLanguageSelected: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        if (isSystemInDarkTheme()) Color.LightGray else Color.Cyan,
                        if (isSystemInDarkTheme())  Color.DarkGray else Color(0xFFFFFFFF)
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.choose_language),
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Blue,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))
        Card(
            modifier = Modifier
                .height(38.dp)
                .width(110.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = {
                mainScreenViewModel.setAppLang("en")
                navController.navigate("floor")
                onLanguageSelected("en")
            }
        ) {
            Text(
                text = "English",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp),
                color = Color(0xFF009688)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .height(38.dp)
                .width(110.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = {
                mainScreenViewModel.setAppLang("ru")
                navController.navigate("floor")
                onLanguageSelected("ru")
            }
        ) {
            Text(
                text = "Русский",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp),
                color = Color(0xFF009688)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .height(38.dp)
                .width(110.dp),
            elevation = CardDefaults.cardElevation(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = {
                mainScreenViewModel.setAppLang("uz")
                navController.navigate("floor")
                onLanguageSelected("uz")
            }
        ) {
            Text(
                text = "O'zbekcha",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp),
                color = Color(0xFF009688)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LanguageScreenPreview() {
    LanguageScreen(navController = NavController(LocalContext.current),
        onLanguageSelected = { _ -> })
}