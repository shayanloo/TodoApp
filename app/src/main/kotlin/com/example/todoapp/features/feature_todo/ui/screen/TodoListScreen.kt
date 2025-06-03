package com.example.todoapp.features.feature_todo.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.features.feature_todo.ui.viewmodel.TodoViewModel
import com.example.todoapp.features.feature_todo.ui.component.AddTodoDialog
import com.example.todoapp.features.feature_todo.ui.component.InfoDialog
import com.example.todoapp.features.feature_todo.ui.component.TodoItemComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(listId: Int, viewModel: TodoViewModel, onBack: () -> Unit, isFa: Boolean) {
    val todos by viewModel.getTodosByList(listId).collectAsState()
    var showAddTodoDialog by remember { mutableStateOf(false) }
    var showInfoDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        if (isFa) "تسک\u200Cها" else "Tasks",
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
                    if (isFa) {
                        IconButton(onClick = onBack) {
                            Icon(Icons.Filled.ArrowForward, contentDescription = "بازگشت")
                        }
                    } else {
                        IconButton(onClick = onBack) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                        IconButton(onClick = { showInfoDialog = true }) {
                            Icon(Icons.Default.Info, contentDescription = "Help")
                        }
                    }
                }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(todos, key = { it.id }) { todo ->
                            TodoItemComponent(
                                todo = todo,
                                onToggle = { viewModel.toggleTodo(todo) },
                                onDelete = { viewModel.deleteTodo(todo) }
                            )
                        }
                    }
                }
                FloatingActionButton(
                    onClick = { showAddTodoDialog = true },
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Todo")
                }
                if (showAddTodoDialog) {
                    AddTodoDialog(
                        onDismiss = { showAddTodoDialog = false },
                        onAdd = { title ->
                            viewModel.addTodo(title, listId)
                            showAddTodoDialog = false
                        }
                    )
                }
            }
        }
    )
    if (showInfoDialog) {
        InfoDialog(isFa = isFa, onDismiss = { showInfoDialog = false })
    }
} 