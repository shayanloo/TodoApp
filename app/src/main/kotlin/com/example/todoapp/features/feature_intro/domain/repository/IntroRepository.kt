package com.example.todoapp.features.feature_intro.domain.repository

import com.example.todoapp.features.feature_intro.domain.model.IntroSlide

interface IntroRepository {
    suspend fun shouldShowIntro(): Boolean
    suspend fun setIntroShown(shown: Boolean)
    fun getIntroSlides(): List<IntroSlide>
} 