package ru.kit.rediexpress.ui.fragments.login.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.kit.rediexpress.domain.DomainResult
import ru.kit.rediexpress.domain.LoadingState
import ru.kit.rediexpress.domain.interactors.StartInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(private val startInteractor: StartInteractor) : ViewModel() {

    private val _onboardingStatus: MutableStateFlow<DomainResult<Boolean>> =
        MutableStateFlow(DomainResult.Loading(LoadingState.INITIAL))
    val onboardingStatus = _onboardingStatus.asStateFlow()

    fun getOnboardingStatus() = viewModelScope.launch {
        _onboardingStatus.value = startInteractor.getOnboardingStatus()
    }
}