package com.app.examenapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.examenapp.presentation.screens.history.HistoryScreen
import com.app.examenapp.presentation.screens.home.HomeScreen

sealed class Screen(
    val route: String,
) {
    object Home : Screen("home")

    object History : Screen("history")
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun SentimentNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onHistoryClick = {
                    navController.navigate(Screen.History.route)
                },
            )
        }

        composable(route = Screen.History.route) {
            HistoryScreen(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
