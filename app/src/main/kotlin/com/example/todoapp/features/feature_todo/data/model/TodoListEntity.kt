package com.example.todoapp.features.feature_todo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todolists")
data class TodoListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val emoji: String = "\uD83D\uDCCB"
) 