package ru.kit.rediexpress.ui.fragments.login.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.kit.rediexpress.R
import ru.kit.rediexpress.domain.DomainResult
import ru.kit.rediexpress.domain.LoadingState
import ru.kit.rediexpress.domain.interactors.StartInteractor
import ru.kit.rediexpress.domain.models.OnboardingData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(private val startInteractor: StartInteractor) : ViewModel() {

    private val _isEnd = MutableSharedFlow<Boolean>()
    val isEnd = _isEnd.asSharedFlow()

    private val _onboardingStatus: MutableStateFlow<DomainResult<Unit>> =
        MutableStateFlow(DomainResult.Loading(LoadingState.INITIAL))
    val onboardingStatus = _onboardingStatus.asStateFlow()

    fun getOnboardingData(): List<OnboardingData> {
        return listOf(
            OnboardingData(
                title = "Quick Delivery At Your Doorstep",
                description = "Enjoy quick pick-up and delivery to your destination",
                image = R.drawable.ic_onboarding_1
            ),
            OnboardingData(
                title = "Flexible Payment",
                description = "Different modes of payment either before and after delivery without stress",
                image = R.drawable.ic_onboarding_2
            ),
            OnboardingData(
                title = "Real-time Tracking",
                description = "Track your packages/items from the comfort of your home till final destination",
                image = R.drawable.ic_onboarding_3
            )
        )
    }

    fun changePage(pagePosition: Int) = viewModelScope.launch {
        when (pagePosition) {
            0, 1 -> {
                _isEnd.emit(false)
            }
            2 -> {
                _isEnd.emit(true)
            }
        }
    }

    fun changeOnboardingStatus() = viewModelScope.launch {
        _onboardingStatus.value = startInteractor.setOnboardingStatus(true)
    }
}