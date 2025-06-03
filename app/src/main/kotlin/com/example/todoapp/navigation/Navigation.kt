package com.example.todoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todoapp.features.feature_splash.ui.screen.SplashScreen
import com.example.todoapp.features.feature_intro.ui.screen.IntroScreen
import com.example.todoapp.features.feature_todo.ui.viewmodel.TodoViewModel
import com.example.todoapp.features.feature_todo.ui.screen.MainScreen
import com.example.todoapp.features.feature_search.ui.screen.SearchScreen
import com.example.todoapp.features.feature_todo.ui.screen.TodoListScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    shouldShowIntro: Boolean,
    viewModel: TodoViewModel,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    currentLanguage: String,
    onLanguageChange: (String) -> Unit
) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController, shouldShowIntro)
        }
        composable("intro") {
            IntroScreen(navController)
        }
        composable("main") {
            MainScreen(
                viewModel = viewModel,
                isDarkTheme = isDarkTheme,
                onThemeChange = onThemeChange,
                currentLanguage = currentLanguage,
                onLanguageChange = onLanguageChange,
                searchScreenProvider = { padding, isFa -> SearchScreen(padding, isFa) },
                navController = navController
            )
        }
        composable("todoList/{listId}") { backStackEntry ->
            val listId = backStackEntry.arguments?.getString("listId")?.toIntOrNull()
            if (listId != null) {
                TodoListScreen(
                    listId = listId,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    isFa = currentLanguage == "fa"
                )
            }
        }
    }
} 