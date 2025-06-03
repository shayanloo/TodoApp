package com.example.todoapp.features.feature_todo.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.features.feature_todo.ui.viewmodel.TodoViewModel
import com.example.todoapp.features.feature_todo.ui.component.AddListBottomSheet
import com.example.todoapp.features.feature_todo.ui.component.InfoDialog
import androidx.navigation.NavController
import androidx.compose.material3.FabPosition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: TodoViewModel,
    isFa: Boolean,
    navController: NavController,
    modifier: Modifier = Modifier,
    fabPosition: FabPosition = FabPosition.End
) {
    val lists by viewModel.lists.collectAsState()
    val selectedListId by viewModel.selectedListId.collectAsState()
    val todos = lists.flatMap { list ->
        emptyList<com.example.todoapp.features.feature_todo.domain.model.TodoItem>()
    }
    var showAddListSheet by remember { mutableStateOf(false) }
    var showInfoDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        if (isFa) "خانه" else "Home",
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddListSheet = true }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add List")
            }
        },
        floatingActionButtonPosition = fabPosition
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(lists) { list ->
                    val todosForList by viewModel.getTodosByList(list.id).collectAsState()
                    val taskCount = todosForList.size
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clickable { navController.navigate("todoList/${list.id}") }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(list.emoji, fontSize = MaterialTheme.typography.headlineMedium.fontSize, modifier = Modifier.padding(start = 16.dp))
                            Text(list.title, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 8.dp))
                            Text("(", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(start = 8.dp))
                            Text(taskCount.toString(), style = MaterialTheme.typography.bodySmall)
                            Text(")", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
            AddListBottomSheet(
                show = showAddListSheet,
                onDismiss = { showAddListSheet = false },
                onAdd = { title, emoji ->
                    viewModel.addList(title, emoji)
                    showAddListSheet = false
                }
            )
            if (showInfoDialog) {
                InfoDialog(isFa = isFa, onDismiss = { showInfoDialog = false })
            }
        }
    }
} 