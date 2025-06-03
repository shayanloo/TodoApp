package com.example.todoapp.features.feature_intro.data.repository

import android.content.Context
import com.example.todoapp.features.feature_intro.domain.repository.IntroRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IntroRepositoryModule {
    @Provides
    @Singleton
    fun provideIntroRepository(
        @ApplicationContext context: Context
    ): IntroRepository = IntroRepositoryImpl(context)
} 