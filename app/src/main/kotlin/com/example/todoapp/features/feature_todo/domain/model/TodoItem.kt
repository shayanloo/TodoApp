package com.example.todoapp.features.feature_todo.domain.model

data class TodoItem(
    val id: Int = 0,
    val title: String,
    val isCompleted: Boolean = false,
    val note: String = "",
    val subtasks: List<Subtask> = emptyList(),
    val listId: Int = 0
) 