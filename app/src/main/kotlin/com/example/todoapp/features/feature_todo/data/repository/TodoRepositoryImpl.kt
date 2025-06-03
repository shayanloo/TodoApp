package com.example.todoapp.features.feature_todo.data.repository

import com.example.todoapp.features.feature_todo.data.local.TodoDao
import com.example.todoapp.features.feature_todo.data.model.TodoListEntity
import com.example.todoapp.features.feature_todo.data.model.TodoItemEntity
import com.example.todoapp.features.feature_todo.domain.model.TodoList
import com.example.todoapp.features.feature_todo.domain.model.TodoItem
import com.example.todoapp.features.feature_todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepository {
    override fun getAllLists(): Flow<List<TodoList>> =
        todoDao.getAllLists().map { list ->
            list.map { it.toDomain() }
        }

    override fun getTodosByList(listId: Int): Flow<List<TodoItem>> =
        todoDao.getTodosByList(listId).map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun addList(title: String, emoji: String) {
        todoDao.insertList(TodoListEntity(title = title, emoji = emoji))
    }

    override suspend fun addTodo(title: String, listId: Int) {
        todoDao.insertTodo(TodoItemEntity(title = title, listId = listId))
    }

    override suspend fun toggleTodo(todo: TodoItem) {
        val entity = todo.toEntity()
        todoDao.updateTodo(entity.copy(isCompleted = !entity.isCompleted))
    }

    override suspend fun deleteTodo(todo: TodoItem) {
        todoDao.deleteTodo(todo.toEntity())
    }
}

// Mapper functions
private fun TodoListEntity.toDomain() = TodoList(id, title, emoji)
private fun TodoItemEntity.toDomain() = TodoItem(id, title, isCompleted, note, emptyList(), listId)
private fun TodoItem.toEntity() = TodoItemEntity(id, title, isCompleted, note, "", listId) 