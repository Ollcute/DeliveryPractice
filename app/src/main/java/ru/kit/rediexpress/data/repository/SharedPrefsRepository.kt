package ru.kit.rediexpress.data.repository

import ru.kit.rediexpress.data.model.DataResult

interface SharedPrefsRepository {

    fun getOnboardingStatus(): DataResult<Boolean>
    fun setOnboardingStatus(status: Boolean): DataResult<Unit>
}