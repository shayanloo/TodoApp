package com.example.todoapp.features.feature_intro.domain.use_case

import com.example.todoapp.features.feature_intro.domain.model.IntroSlide
import com.example.todoapp.features.feature_intro.domain.repository.IntroRepository
import javax.inject.Inject

class GetIntroSlidesUseCase @Inject constructor(
    private val repository: IntroRepository
) {
    operator fun invoke(): List<IntroSlide> = repository.getIntroSlides()
} 