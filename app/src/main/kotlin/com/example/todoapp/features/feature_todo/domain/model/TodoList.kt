package com.example.todoapp.features.feature_todo.domain.model

data class TodoList(
    val id: Int = 0,
    val title: String,
    val emoji: String = "\uD83D\uDCCB"
) 