package com.example.todoapp.features.feature_todo.domain.use_case

import com.example.todoapp.features.feature_todo.domain.model.TodoItem
import com.example.todoapp.features.feature_todo.domain.repository.TodoRepository
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend operator fun invoke(todo: TodoItem) {
        repository.deleteTodo(todo)
    }
} 