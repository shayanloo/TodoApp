package com.example.todoapp.features.feature_todo.data.model

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromSubtasks(subtasks: List<SubtaskEntity>): String {
        return subtasks.joinToString("|") { "${it.id},${it.title},${it.isCompleted}" }
    }

    @TypeConverter
    fun toSubtasks(data: String): List<SubtaskEntity> {
        if (data.isEmpty()) return emptyList()
        return data.split("|").map {
            val (id, title, completed) = it.split(",")
            SubtaskEntity(id.toInt(), title, completed.toBoolean())
        }
    }
} 