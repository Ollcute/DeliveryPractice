package ru.kit.rediexpress.domain.interactors

import ru.kit.rediexpress.domain.DomainResult

interface StartInteractor {

    fun getOnboardingStatus(): DomainResult<Boolean>
    fun setOnboardingStatus(status: Boolean): DomainResult<Unit>


}