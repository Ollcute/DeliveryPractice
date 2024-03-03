package ru.kit.rediexpress.data.repository.impl

import ru.kit.rediexpress.data.model.DataResult
import ru.kit.rediexpress.data.repository.SharedPrefsRepository
import ru.kit.rediexpress.data.sharedPrefs.SharedPrefsHandler

class SharedPrefsRepositoryImpl(private val handler: SharedPrefsHandler) : SharedPrefsRepository {

    override fun getOnboardingStatus(): DataResult<Boolean> = DataResult.Success(handler.getOnboardingStatus())

    override fun setOnboardingStatus(status: Boolean): DataResult<Unit> = DataResult.Success(handler.setOnboardingStatus(status))
}