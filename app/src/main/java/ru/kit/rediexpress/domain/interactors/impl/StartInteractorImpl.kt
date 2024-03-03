package ru.kit.rediexpress.domain.interactors.impl

import ru.kit.rediexpress.data.repository.SharedPrefsRepository
import ru.kit.rediexpress.domain.DomainResult
import ru.kit.rediexpress.domain.interactors.BaseInteractor
import ru.kit.rediexpress.domain.interactors.StartInteractor

class StartInteractorImpl(private val sharedPrefs: SharedPrefsRepository) : BaseInteractor(), StartInteractor {

    override fun getOnboardingStatus(): DomainResult<Boolean> = returnDomainResult {
        sharedPrefs.getOnboardingStatus()
    }

    override fun setOnboardingStatus(status: Boolean): DomainResult<Unit> = returnDomainResult {
        sharedPrefs.setOnboardingStatus(status)
    }

}