package com.example.todoapp.features.feature_todo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.features.feature_todo.domain.model.TodoItem
import com.example.todoapp.features.feature_todo.domain.use_case.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getAllListsUseCase: GetAllListsUseCase,
    private val getTodosByListUseCase: GetTodosByListUseCase,
    private val addListUseCase: AddListUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {
    val lists = getAllListsUseCase().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    private val _selectedListId = MutableStateFlow<Int?>(null)
    val selectedListId: StateFlow<Int?> = _selectedListId.asStateFlow()
    val todos = selectedListId.value?.let { id ->
        getTodosByListUseCase(id).stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }

    fun selectList(listId: Int?) {
        _selectedListId.value = listId
    }

    fun addList(title: String, emoji: String) {
        viewModelScope.launch { addListUseCase(title, emoji) }
    }

    fun addTodo(title: String, listId: Int) {
        viewModelScope.launch { addTodoUseCase(title, listId) }
    }

    fun toggleTodo(todo: TodoItem) {
        viewModelScope.launch { toggleTodoUseCase(todo) }
    }

    fun deleteTodo(todo: TodoItem) {
        viewModelScope.launch { deleteTodoUseCase(todo) }
    }

    fun getTodosByList(listId: Int): StateFlow<List<TodoItem>> =
        getTodosByListUseCase(listId).stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList()
        )
} 