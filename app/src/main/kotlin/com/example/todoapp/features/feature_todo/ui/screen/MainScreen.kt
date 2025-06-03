package com.example.todoapp.features.feature_todo.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.platform.LocalLayoutDirection
import com.example.todoapp.features.feature_todo.ui.viewmodel.TodoViewModel
import com.example.todoapp.features.feature_settings.ui.screen.SettingsScreen
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.material3.FabPosition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: TodoViewModel,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    currentLanguage: String,
    onLanguageChange: (String) -> Unit,
    searchScreenProvider: @Composable (PaddingValues, Boolean) -> Unit,
    navController: NavController
) {
    val isFa = currentLanguage == "fa"
    CompositionLocalProvider(LocalLayoutDirection provides if (isFa) LayoutDirection.Rtl else LayoutDirection.Ltr) {
        var selectedTab by remember { mutableStateOf(0) }
        Scaffold(
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(Icons.Outlined.Search, contentDescription = "جستجو") },
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Outlined.Check, contentDescription = "خانه") },
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Outlined.Settings, contentDescription = "تنظیمات") },
                        selected = selectedTab == 2,
                        onClick = { selectedTab = 2 }
                    )
                }
            }
        ) { padding ->
            when (selectedTab) {
                0 -> HomeScreen(
                    viewModel,
                    isFa,
                    navController,
                    modifier = Modifier.padding(padding),
                    fabPosition = if (isFa) FabPosition.Start else FabPosition.End
                )
                1 -> searchScreenProvider(padding, isFa)
                2 -> SettingsScreen(
                    navController = null,
                    isDarkTheme = isDarkTheme,
                    onThemeChange = onThemeChange,
                    currentLanguage = currentLanguage,
                    onLanguageChange = onLanguageChange,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
} 