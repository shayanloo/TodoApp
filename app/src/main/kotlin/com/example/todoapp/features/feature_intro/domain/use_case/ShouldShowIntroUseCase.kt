package com.example.todoapp.features.feature_intro.domain.use_case

import com.example.todoapp.features.feature_intro.domain.repository.IntroRepository
import javax.inject.Inject

class ShouldShowIntroUseCase @Inject constructor(
    private val repository: IntroRepository
) {
    suspend operator fun invoke(): Boolean = repository.shouldShowIntro()
} 