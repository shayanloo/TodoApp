package com.example.todoapp.di

import com.example.todoapp.features.feature_todo.domain.repository.TodoRepository
import com.example.todoapp.features.feature_todo.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoUseCaseModule {
    @Provides
    @Singleton
    fun provideGetAllListsUseCase(repository: TodoRepository) = GetAllListsUseCase(repository)

    @Provides
    @Singleton
    fun provideAddListUseCase(repository: TodoRepository) = AddListUseCase(repository)

    @Provides
    @Singleton
    fun provideAddTodoUseCase(repository: TodoRepository) = AddTodoUseCase(repository)

    @Provides
    @Singleton
    fun provideGetTodosByListUseCase(repository: TodoRepository) = GetTodosByListUseCase(repository)

    @Provides
    @Singleton
    fun provideToggleTodoUseCase(repository: TodoRepository) = ToggleTodoUseCase(repository)

    @Provides
    @Singleton
    fun provideDeleteTodoUseCase(repository: TodoRepository) = DeleteTodoUseCase(repository)
} 