package com.example.todoapp.features.feature_splash.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
// import androidx.compose.material3.Text // Commented out as it's not used
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
// import androidx.compose.ui.graphics.Color // Commented out as it's not used
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
// import androidx.compose.ui.unit.sp // Commented out as it's not used
import kotlinx.coroutines.delay
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.todoapp.R

@Composable
fun SplashScreen(navController: NavController, shouldShowIntro: Boolean) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
        delay(1500)
        if (shouldShowIntro) {
            navController.navigate("intro") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            navController.navigate("main") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surface
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedVisibility(visible = visible, enter = fadeIn()) {
                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = stringResource(id = R.string.splash_logo_desc),
                    modifier = Modifier.size(120.dp)
                )
            }
//            Spacer(modifier = Modifier.height(24.dp))
//            AnimatedVisibility(visible = visible, enter = fadeIn()) {
//                Text(
//                    text = stringResource(id = R.string.app_name),
//                    style = MaterialTheme.typography.titleLarge,
//                    color = Color.White,
//                    modifier = Modifier.padding(top = 8.dp)
//                )
//            }
        }
    }
} 