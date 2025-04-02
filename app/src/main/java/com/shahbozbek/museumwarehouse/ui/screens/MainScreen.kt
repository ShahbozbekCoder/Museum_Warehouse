package com.shahbozbek.museumwarehouse.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
    floor: Int? = null,
    paddingValues: PaddingValues
) {
    LaunchedEffect(Unit) {
        mainScreenViewModel.getAll()
    }
    val articles: MutableList<Articles>
    val articles1 = mutableListOf(
        Articles(title = stringResource(R.string.pochta_aloqasi)),
        Articles(title = stringResource(R.string.teleminora_maketlari_ekspozitsiyasi)),
        Articles(title = stringResource(R.string.grammofon_va_radioqabulqilgichlari_ekspozitsiyasi))
    )
    val articles2 = mutableListOf(
        Articles(title = stringResource(R.string.radioreproduktor)),
        Articles(title = stringResource(R.string.fanograf)),
        Articles(title = stringResource(R.string.m_60_kommutatori))
    )
    val articles0 = mutableListOf(
        Articles(title = stringResource(R.string._6n_1_rusumli_lampali_radioqabulqilgich)),
        Articles(title = stringResource(R.string.elektron_nurli_trubka)),
        Articles(title = stringResource(R.string.xarf_teruvchi_telegraf_apparati))
    )
    articles = when (floor) {
        0 -> {
            articles0
        }
        1 -> {
            articles1
        }
        2 -> {
            articles2
        }
        else -> {
            articles1
        }
    }
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
                        navController.navigate("articles/$index/$floor")
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
