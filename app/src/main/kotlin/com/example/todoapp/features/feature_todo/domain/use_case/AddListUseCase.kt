package com.example.todoapp.features.feature_todo.domain.use_case

import com.example.todoapp.features.feature_todo.domain.repository.TodoRepository
import javax.inject.Inject

class AddListUseCase @Inject constructor(private val repository: TodoRepository) {
    suspend operator fun invoke(title: String, emoji: String) {
        repository.addList(title, emoji)
    }
} 