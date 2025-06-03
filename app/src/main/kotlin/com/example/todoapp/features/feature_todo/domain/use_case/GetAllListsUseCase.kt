package com.example.todoapp.features.feature_todo.domain.use_case

import com.example.todoapp.features.feature_todo.domain.repository.TodoRepository
import javax.inject.Inject

class GetAllListsUseCase @Inject constructor(private val repository: TodoRepository) {
    operator fun invoke() = repository.getAllLists()
} 