package com.example.todoapp.features.feature_search.ui.screen

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.filled.Info

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(padding: PaddingValues, isFa: Boolean) {
    var searchText by remember { mutableStateOf("") }
    var showInfoDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    if (isFa) "جستجو" else "Search",
                    style = MaterialTheme.typography.titleLarge
                )
            },
            actions = {
                if (isFa) {
                    IconButton(onClick = { showInfoDialog = true }) {
                        Icon(Icons.Default.Info, contentDescription = "راهنما")
                    }
                }
            },
            navigationIcon = {
                if (!isFa) {
                    IconButton(onClick = { showInfoDialog = true }) {
                        Icon(Icons.Default.Info, contentDescription = "Help")
                    }
                }
            }
        )
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text(if (isFa) "جستجو" else "Search") },
            modifier = Modifier.fillMaxWidth()
        )
        // محتوای جستجو (در حال حاضر خالی)
    }
    if (showInfoDialog) {
        AlertDialog(
            onDismissRequest = { showInfoDialog = false },
            title = { Text(if (isFa) "راهنمای این بخش" else "Section Guide") },
            text = { Text(if (isFa) "این بخش برای مدیریت و مشاهده اطلاعات مربوط به این قسمت است." else "This section is for managing and viewing information related to this part.") },
            confirmButton = {
                TextButton(onClick = { showInfoDialog = false }) {
                    Text(if (isFa) "باشه" else "OK")
                }
            }
        )
    }
} 