package com.muratguzel.tastygo.presentation.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratguzel.tastygo.util.Prefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val prefs: Prefs
) : ViewModel() {

    // Loading -> ShowOnboarding(false) -> Skip(true)
    val shouldSkipOnboarding: StateFlow<Boolean> =
        prefs.onboardingSeen
            .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun completeOnboarding() {
        viewModelScope.launch { prefs.setOnboardingSeen(true) }
    }
}