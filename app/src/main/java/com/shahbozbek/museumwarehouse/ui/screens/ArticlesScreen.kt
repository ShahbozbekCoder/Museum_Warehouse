package com.shahbozbek.museumwarehouse.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.shahbozbek.museumwarehouse.R
import com.shahbozbek.museumwarehouse.data.ArticlesData

@Composable
fun ArticlesScreen(
    navController: NavController,
    index: Int? = null,
    paddingValues: PaddingValues
) {
    val articles: MutableList<ArticlesData>
    val articles1 = mutableListOf(
        ArticlesData(
            title = stringResource(R.string.ot_arava_ekspozitsiyasi),
            content = stringResource(R.string.ot_arava_haqida),
            image = R.drawable.carriage
        ),
        ArticlesData(
            title = stringResource(R.string.pochta_aloqasi_tarixi),
            content = stringResource(R.string.pochta_aloqasi_haqida),
            image = R.drawable.postal
        )
    )
    val articles2 = mutableListOf(
        ArticlesData(
            title = stringResource(R.string.teleminora_tarixi),
            content = stringResource(R.string.teleminora_haqida),
            image = R.drawable.tv_tower
        ),
        ArticlesData(
            title = stringResource(R.string.pul_markasi),
            content = stringResource(R.string.pochta_aloqasi_haqida),
            image = R.drawable.marks
        )
    )
    val articles3 = mutableListOf(
        ArticlesData(
            title = stringResource(R.string.grammofon_tarixi),
            content = stringResource(R.string.grammofon_haqida),
            image = R.drawable.grammofon
        ),
        ArticlesData(
            title = stringResource(R.string.radioqabulqilgich_tarixi),
            content = stringResource(R.string.radioqabulqilgich_haqida),
            image = R.drawable.radio
        )
    )
    articles = when (index) {
        0 -> {
            articles1
        }

        1 -> {
            articles2
        }

        2 -> {
            articles3
        }

        else -> {
            articles1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(articles.size) { index ->
                val item = articles[index]
                val json = Uri.encode(Gson().toJson(item))
                ArticlesItem(
                    article = articles[index].title,
                    onClick = {
                        navController.navigate("view_article?article=$json")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticlesScreenPreview() {
    ArticlesScreen(
        navController = NavController(LocalContext.current),
        index = 0,
        paddingValues = PaddingValues(0.dp)
    )
}