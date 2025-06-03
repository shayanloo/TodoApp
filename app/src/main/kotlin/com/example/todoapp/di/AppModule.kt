package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.core_common.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTodoDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    )
    .fallbackToDestructiveMigration()
    .build()

    @Provides
    @Singleton
    fun provideTodoDao(db: AppDatabase) = db.todoDao()
}