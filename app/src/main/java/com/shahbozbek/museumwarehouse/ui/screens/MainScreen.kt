package com.shahbozbek.museumwarehouse.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel
) {
    LaunchedEffect(Unit) {
        mainScreenViewModel.getAll()
    }
    val itemsList = mainScreenViewModel.items.collectAsState().value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add") },
                modifier = Modifier.padding(16.dp),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Museum Warehouse Details")
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyVerticalGrid(
                modifier = Modifier.weight(1f),
                columns = GridCells.Fixed(2)
            ) {
                if (itemsList.isNotEmpty()) {
                    items(itemsList.size) { index ->
                        ItemCard(
                            onClick = {
                                val item = itemsList[index]
                                val json = Uri.encode(Gson().toJson(item))
                                navController.navigate("add?item=$json")
                            },
                            myItems = itemsList[index]
                        )
                    }
                }
            }
            if (itemsList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No items found")
                }
            }
        }
    }
}
