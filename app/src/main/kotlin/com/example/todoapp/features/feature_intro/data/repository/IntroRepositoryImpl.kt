package com.example.todoapp.features.feature_intro.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.todoapp.features.feature_intro.domain.model.IntroSlide
import com.example.todoapp.features.feature_intro.domain.repository.IntroRepository
import javax.inject.Inject

class IntroRepositoryImpl @Inject constructor(
    context: Context
) : IntroRepository {
    private val prefs: SharedPreferences = context.getSharedPreferences("todo_prefs", Context.MODE_PRIVATE)
    override suspend fun shouldShowIntro(): Boolean = prefs.getBoolean("show_intro", true)
    override suspend fun setIntroShown(shown: Boolean) {
        prefs.edit().putBoolean("show_intro", shown).apply()
    }

    override fun getIntroSlides(): List<IntroSlide> = listOf(
        IntroSlide(
            imageRes = com.example.todoapp.R.drawable.ic_intro_1,
            title = "سازماندهی وظایف",
            desc = "تسک‌هایت را دسته‌بندی و مدیریت کن"
        ),
        IntroSlide(
            imageRes = com.example.todoapp.R.drawable.ic_intro_2,
            title = "یادداشت و زیرتسک",
            desc = "برای هر تسک یادداشت و مراحل اضافه کن"
        ),
        IntroSlide(
            imageRes = com.example.todoapp.R.drawable.ic_intro_3,
            title = "تجربه کاربری مدرن",
            desc = "تم روشن/تیره و زبان قابل انتخاب"
        )
    )
} 