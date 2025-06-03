package com.example.todoapp.features.feature_intro.domain.use_case

import com.example.todoapp.features.feature_intro.domain.repository.IntroRepository
import javax.inject.Inject

class SetIntroShownUseCase @Inject constructor(
    private val repository: IntroRepository
) {
    suspend operator fun invoke(shown: Boolean) = repository.setIntroShown(shown)
} 