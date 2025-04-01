package com.shahbozbek.museumwarehouse.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.shahbozbek.museumwarehouse.R

@Composable
fun MainScreen(
    navController: NavController,
    mainScreenViewModel: MainScreenViewModel,
    paddingValues: PaddingValues
) {
    LaunchedEffect(Unit) {
        mainScreenViewModel.getAll()
    }
    val articles = mutableListOf(
        Articles(title = stringResource(R.string.pochta_aloqasi)),
        Articles(title = stringResource(R.string.teleminora_maketlari_ekspozitsiyasi)),
        Articles(title = stringResource(R.string.grammofon_va_radioqabulqilgichlari_ekspozitsiyasi))
    )
    val itemsList = mainScreenViewModel.items.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(articles.size) { index ->
                ArticlesItem(
                    article = articles[index].title,
                    onClick = {
                        navController.navigate("articles/$index")
                    }
                )
            }
        }
    }

}
/*            LazyVerticalGrid(
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
                            myItems = itemsList[index]x
                        )
                    }
                }
            }
            if (itemsList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(R.string.no_items_found))
                }
            }*/
