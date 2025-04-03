package com.shahbozbek.museumwarehouse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shahbozbek.museumwarehouse.navigation.MyNavigation
import com.shahbozbek.museumwarehouse.ui.screens.MainScreenViewModel
import com.shahbozbek.museumwarehouse.ui.theme.MuseumWarehouseTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appLang = viewModel.getAppLang()
        setLocale(appLang)

        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            val navigationState = navController.currentBackStackEntryAsState()
            val currentRoute = navigationState.value?.destination?.route

            MuseumWarehouseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = stringResource(R.string.museum_warehouse_details))
                            },
                            navigationIcon = {
                                if (currentRoute != "language") IconButton(
                                    onClick = {
                                        navController.popBackStack()
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Menu"
                                    )
                                }
                            }
                        )
                    },
                ) { paddingValues ->
                    MyNavigation(
                        navController = navController,
                        onLanguageSelected = { lang ->
                            setLocale(lang)
                        },
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }

    private fun setLocale(languageCode: String) {

        val currentLocale = resources.configuration.locales[0]

        if (currentLocale.language == languageCode) return

        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)

        resources.updateConfiguration(config, resources.displayMetrics)

        recreate()
    }
}
