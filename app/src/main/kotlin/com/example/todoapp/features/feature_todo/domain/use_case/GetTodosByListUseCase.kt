package com.example.todoapp.features.feature_todo.domain.use_case

import com.example.todoapp.features.feature_todo.domain.repository.TodoRepository
import javax.inject.Inject

class GetTodosByListUseCase @Inject constructor(private val repository: TodoRepository) {
    operator fun invoke(listId: Int) = repository.getTodosByList(listId)
} 