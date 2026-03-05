package com.hugopolog.demoappopen.ui.feature.onboarding

import com.hugopolog.demoappopen.navigation.AppScreens
import com.hugopolog.demoappopen.ui.feature.BaseViewModel
import com.hugopolog.demoappopen.ui.feature.main.MainActions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
) : BaseViewModel() {
    fun onAction(action: OnboardingActions) {
        when (action) {
            is OnboardingActions.NavigateToMain -> {
                navigate(AppScreens.MainScreen)
            }
        }
    }
}