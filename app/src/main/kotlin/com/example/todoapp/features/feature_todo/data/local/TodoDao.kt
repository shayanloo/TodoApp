package com.example.todoapp.features.feature_todo.data.local

import androidx.room.*
import com.example.todoapp.features.feature_todo.data.model.TodoListEntity
import com.example.todoapp.features.feature_todo.data.model.TodoItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    // TodoList methods
    @Query("SELECT * FROM todolists")
    fun getAllLists(): Flow<List<TodoListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: TodoListEntity): Long

    @Delete
    suspend fun deleteList(list: TodoListEntity)

    // TodoItem methods
    @Query("SELECT * FROM todos WHERE listId = :listId")
    fun getTodosByList(listId: Int): Flow<List<TodoItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoItemEntity)

    @Delete
    suspend fun deleteTodo(todo: TodoItemEntity)

    @Update
    suspend fun updateTodo(todo: TodoItemEntity)
} 