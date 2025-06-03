package com.example.todoapp.features.feature_todo.domain.model

data class Subtask(
    val id: Int,
    val title: String,
    val isCompleted: Boolean = false
) 