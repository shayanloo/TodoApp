package com.example.todoapp.features.feature_todo.data.model

data class SubtaskEntity(
    val id: Int,
    val title: String,
    val isCompleted: Boolean = false
) 