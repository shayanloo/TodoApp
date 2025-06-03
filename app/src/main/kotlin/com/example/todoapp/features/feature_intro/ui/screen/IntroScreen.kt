package com.example.todoapp.features.feature_intro.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoapp.features.feature_intro.ui.viewmodel.IntroViewModel

@Composable
fun IntroScreen(navController: NavController, viewModel: IntroViewModel = hiltViewModel()) {
    val slides = viewModel.slides
    var current by remember { mutableStateOf(0) }
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(current) { visible = false; visible = true }

    LaunchedEffect(Unit) {
        viewModel.loadShouldShowIntro()
    }

    val shouldNavigate by remember { mutableStateOf(false) }
    val shouldShowIntro by viewModel.shouldShowIntro.collectAsState()

    LaunchedEffect(shouldShowIntro) {
        if (shouldShowIntro == false) {
            navController.navigate("main") {
                popUpTo("intro") { inclusive = true }
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
        Card(
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(visible = visible, enter = fadeIn()) {
                    Image(
                        painter = painterResource(id = slides[current].imageRes),
                        contentDescription = null,
                        modifier = Modifier.size(120.dp)
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                AnimatedVisibility(visible = visible, enter = fadeIn()) {
                    Text(
                        text = slides[current].title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                AnimatedVisibility(visible = visible, enter = fadeIn()) {
                    Text(
                        text = slides[current].desc,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    slides.forEachIndexed { index, _ ->
                        Box(
                            modifier = Modifier
                                .size(if (index == current) 16.dp else 10.dp)
                                .clip(CircleShape)
                                .background(
                                    if (index == current) MaterialTheme.colorScheme.primary else Color.Gray.copy(alpha = 0.3f)
                                )
                        )
                        if (index != slides.lastIndex) Spacer(modifier = Modifier.width(8.dp))
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        if (current < slides.lastIndex) {
                            current++
                        } else {
                            viewModel.setIntroShown(false)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = if (current < slides.lastIndex) stringResource(id = R.string.intro_next) else stringResource(id = R.string.intro_start),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

data class IntroSlide(val imageRes: Int, val title: String, val desc: String) 