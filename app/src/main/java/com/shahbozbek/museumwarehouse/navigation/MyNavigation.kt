package com.shahbozbek.museumwarehouse.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.shahbozbek.museumwarehouse.data.ArticlesData
import com.shahbozbek.museumwarehouse.data.local.Items
import com.shahbozbek.museumwarehouse.ui.screens.AddItemScreen
import com.shahbozbek.museumwarehouse.ui.screens.Articles
import com.shahbozbek.museumwarehouse.ui.screens.ArticlesScreen
import com.shahbozbek.museumwarehouse.ui.screens.FloorScreen
import com.shahbozbek.museumwarehouse.ui.screens.LanguageScreen
import com.shahbozbek.museumwarehouse.ui.screens.MainScreen
import com.shahbozbek.museumwarehouse.ui.screens.ViewArticleScreen

@Composable
fun MyNavigation(
    onLanguageSelected: (String) -> Unit,
    paddingValues: PaddingValues
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "language") {
        composable("language") {
            LanguageScreen(navController = navController, onLanguageSelected = onLanguageSelected )
        }
        composable("floor") {
            FloorScreen(navController)
        }
        composable("main") {
            MainScreen(navController, hiltViewModel(), paddingValues)
        }
        composable(
            route = "add?item={item}",
            arguments = listOf(navArgument("item") {
                type = NavType.StringType
                nullable = true
            } )
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("item")
            val item = Gson().fromJson(json, Items::class.java)
            AddItemScreen(navController, hiltViewModel(), item)
        }
        composable(
            route = "articles/{index}",
            arguments = listOf(navArgument("index") {
                type = NavType.IntType
            })
        ) {backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index")
            ArticlesScreen(
                navController = navController,
                index = index,
                paddingValues = paddingValues
            )
        }
        composable(
            route = "view_article?article={article}",
            arguments = listOf(navArgument("article") {
                type = NavType.StringType
                nullable = true
            })
        ) {backStackEntry ->
            val json = backStackEntry.arguments?.getString("article")
            val article = Gson().fromJson(json, ArticlesData::class.java)
            ViewArticleScreen(
                article = article,
                paddingValues
            )
        }
    }
}
