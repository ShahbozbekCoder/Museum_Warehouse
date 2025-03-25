package com.shahbozbek.museumwarehouse.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.shahbozbek.museumwarehouse.data.local.Items
import com.shahbozbek.museumwarehouse.ui.screens.AddItemScreen
import com.shahbozbek.museumwarehouse.ui.screens.MainScreen

@Composable
fun MyNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController, hiltViewModel())
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
    }
}
