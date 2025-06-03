package com.example.todoapp.features.feature_todo.domain.repository

import com.example.todoapp.features.feature_todo.domain.model.TodoList
import com.example.todoapp.features.feature_todo.domain.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAllLists(): Flow<List<TodoList>>
    fun getTodosByList(listId: Int): Flow<List<TodoItem>>
    suspend fun addList(title: String, emoji: String)
    suspend fun addTodo(title: String, listId: Int)
    suspend fun toggleTodo(todo: TodoItem)
    suspend fun deleteTodo(todo: TodoItem)
} 