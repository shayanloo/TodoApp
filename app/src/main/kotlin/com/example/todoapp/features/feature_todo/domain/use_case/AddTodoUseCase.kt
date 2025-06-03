package com.example.todoapp.features.feature_todo.domain.use_case

import com.example.todoapp.features.feature_todo.domain.repository.TodoRepository
import javax.inject.Inject

class AddTodoUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend operator fun invoke(title: String, listId: Int) {
        repository.addTodo(title, listId)
    }
} 