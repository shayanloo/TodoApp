package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.features.feature_todo.ui.viewmodel.TodoViewModel
import com.example.todoapp.core_common.designsystem.TodoAppTheme
import com.example.todoapp.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        
        val prefs = getSharedPreferences("todo_prefs", Context.MODE_PRIVATE)
        val lang = prefs.getString("language", "fa") ?: "fa"
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        val shouldShowIntro = prefs.getBoolean("show_intro", true)

        setContent {
            val prefs = getSharedPreferences("todo_prefs", Context.MODE_PRIVATE)
            var isDarkTheme by remember { mutableStateOf(prefs.getBoolean("dark_theme", false)) }
            var currentLanguage by remember { mutableStateOf(lang) }
            TodoAppTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()
                val viewModel: TodoViewModel = hiltViewModel()
                AppNavHost(
                    navController = navController,
                    shouldShowIntro = shouldShowIntro,
                    viewModel = viewModel,
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { isDark ->
                        isDarkTheme = isDark
                        prefs.edit().putBoolean("dark_theme", isDark).apply()
                    },
                    currentLanguage = currentLanguage,
                    onLanguageChange = { lang ->
                        currentLanguage = lang
                        prefs.edit().putString("language", lang).apply()
                        val locale = Locale(lang)
                        Locale.setDefault(locale)
                        val config = resources.configuration
                        config.setLocale(locale)
                        resources.updateConfiguration(config, resources.displayMetrics)
                    }
                )
            }
        }
    }
}