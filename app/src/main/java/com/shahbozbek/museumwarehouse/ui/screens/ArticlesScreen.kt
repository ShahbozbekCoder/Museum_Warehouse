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
    paddingValues: PaddingValues,
    floor: Int? = null
) {
    val articles: MutableList<ArticlesData>
    val articles11 = mutableListOf(
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
    val articles12 = mutableListOf(
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
    val articles13 = mutableListOf(
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
    val articles21 = mutableListOf(
        ArticlesData(
            title = stringResource(R.string.radioreproduktor_tarixi),
            content = stringResource(R.string.radioreproduktor_haqida),
            image = R.drawable.radioreproduktov
        ),
        ArticlesData(
            title = stringResource(R.string.rekord_19_rusumli_ovoz_kuchaytirgich_tarixi),
            content = stringResource(R.string.rekord_19_rusumli_ovoz_kuchaytirgich_haqida),
            image = R.drawable.amplifier
        )
    )
    val articles22 = mutableListOf(
        ArticlesData(
            title = stringResource(R.string.fanograf_tarixi),
            content = stringResource(R.string.fanograf_haqida),
            image = R.drawable.fonograf
        ),
        ArticlesData(
            title = stringResource(R.string.patefon_tarixi),
            content = stringResource(R.string.patefon_haqida),
            image = R.drawable.patefon
        )
    )
    val articles23 = mutableListOf(
        ArticlesData(
            title = stringResource(R.string.m_60_kommukatori_tarixi),
            content = stringResource(R.string.m_60_kommukatori_haqida),
            image = R.drawable.switcher
        ),
        ArticlesData(
            title = stringResource(R.string.morze_apparati_tarixi),
            content = stringResource(R.string.morze_apparati_haqida),
            image = R.drawable.morze
        )
    )
    val articles01 = mutableListOf(
        ArticlesData(
            title = stringResource(R.string._6n_1_rusumli_lampali_radioqabulqilgich_tarixi),
            content = stringResource(R.string._6n_1_rusumli_lampali_radioqabulqilgich_haqida),
            image = R.drawable.radio_receiver
        ),
        ArticlesData(
            title = stringResource(R.string.kvn_49_televizori_tarixi),
            content = stringResource(R.string.kvn_49_televizori_haqida),
            image = R.drawable.tv
        )
    )
    val articles02 = mutableListOf(
        ArticlesData(
            title = stringResource(R.string.elektron_nurli_trubka_tarixi),
            content = stringResource(R.string.elektron_nurli_trubka_haqida),
            image = R.drawable.cathode
        ),
        ArticlesData(
            title = stringResource(R.string.luna_24_kosmik_stansiyasi_maketi_tarixi),
            content = stringResource(R.string.luna_24_kosmik_stansiyasi_maketi_haqida),
            image = R.drawable.lander
        )
    )
    val articles03 = mutableListOf(
        ArticlesData(
            title = stringResource(R.string.xarf_teruvchi_telegraf_apparati_tarixi),
            content = stringResource(R.string.xarf_teruvchi_telegraf_apparati_haqida),
            image = R.drawable.telegraph
        ),
        ArticlesData(
            title = stringResource(R.string.raqam_terilmaydigan_telefon_apparati_tarixi),
            content = stringResource(R.string.raqam_terilmaydigan_telefon_apparati_haqida),
            image = R.drawable.telephone
        )
    )

    articles = when (floor) {
        0 -> {
            when(index) {
                0 -> {
                    articles01
                }
                1 -> {
                    articles02
                    }
                2 -> {
                    articles03
                }
                else -> {
                    articles01
                }
            }
        }

        1 -> {
            when(index) {
                0 -> {
                    articles11
                }
                1 -> {
                    articles12
                }
                2 -> {
                    articles13
                }
                else -> {
                    articles11
                }
            }
        }

        2 -> {
            when(index) {
                0 -> {
                    articles21
                }
                1 -> {
                    articles22
                    }
                2 -> {
                    articles23
                }
                else -> {
                    articles21
                }
            }
        }

        else -> {
            articles11
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