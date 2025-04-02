package com.shahbozbek.museumwarehouse.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.shahbozbek.museumwarehouse.R

@Composable
fun FloorScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isSystemInDarkTheme()) Color(0xFF1C1B1F)else Color.White
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                val index = 1
                navController.navigate("main/$index")
            },
            modifier = Modifier
                .background(Color.White)
                .width(300.dp)
                .background(
                    color = if(isSystemInDarkTheme()) Color(0xFF1C1B1F) else Color.White
                )
        ) {
            Text(text = stringResource(R.string.first_floor))
        }
        Button(
            onClick = {
                val index = 2
                navController.navigate("main/$index")
            },
            modifier = Modifier
                .background(Color.White)
                .width(300.dp)
                .background(
                    color = if(isSystemInDarkTheme()) Color(0xFF1C1B1F) else Color.White
                )
        ) {
            Text(text = stringResource(R.string.second_floor))
        }
        Button(
            onClick = {
                val index = 0
                navController.navigate("main/$index")
            },
            modifier = Modifier
                .background(Color.White)
                .width(300.dp)
                .background(
                    color = if(isSystemInDarkTheme()) Color(0xFF1C1B1F) else Color.White
                )
        ) {
            Text(text = stringResource(R.string.lower_floor))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FloorScreenPreview() {
    FloorScreen(navController = NavController(LocalContext.current))
}