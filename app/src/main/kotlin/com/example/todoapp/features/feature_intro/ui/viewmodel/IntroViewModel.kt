package com.example.todoapp.features.feature_intro.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.features.feature_intro.domain.use_case.ShouldShowIntroUseCase
import com.example.todoapp.features.feature_intro.domain.use_case.SetIntroShownUseCase
import com.example.todoapp.features.feature_intro.domain.use_case.GetIntroSlidesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val shouldShowIntroUseCase: ShouldShowIntroUseCase,
    private val setIntroShownUseCase: SetIntroShownUseCase,
    private val getIntroSlidesUseCase: GetIntroSlidesUseCase
) : ViewModel() {
    private val _shouldShowIntro = MutableStateFlow<Boolean?>(null)
    val shouldShowIntro: StateFlow<Boolean?> = _shouldShowIntro

    val slides: List<com.example.todoapp.features.feature_intro.domain.model.IntroSlide> = getIntroSlidesUseCase()

    fun loadShouldShowIntro() {
        viewModelScope.launch {
            _shouldShowIntro.value = shouldShowIntroUseCase()
        }
    }

    fun setIntroShown(shown: Boolean) {
        viewModelScope.launch {
            setIntroShownUseCase(shown)
            _shouldShowIntro.value = shouldShowIntroUseCase()
        }
    }
} 