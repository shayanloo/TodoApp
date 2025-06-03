package com.example.todoapp.core_common.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapp.features.feature_todo.data.model.Converters
import com.example.todoapp.features.feature_todo.data.local.TodoDao
import com.example.todoapp.features.feature_todo.data.model.TodoItemEntity
import com.example.todoapp.features.feature_todo.data.model.TodoListEntity

@Database(entities = [TodoListEntity::class, TodoItemEntity::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao


    companion object {
        const val DATABASE_NAME = "notesX_db"
    }


} 