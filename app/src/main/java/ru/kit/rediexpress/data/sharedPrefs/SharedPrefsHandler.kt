package ru.kit.rediexpress.data.sharedPrefs

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SharedPrefsHandler(context: Context) {

    val prefs: SharedPreferences =
        EncryptedSharedPreferences.create(
            context,
            PREFERENCES_FILE_NAME,
            MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun getOnboardingStatus(): Boolean =
        prefs.getBoolean(SharedPrefsKeys.ONBOARDING_STATUS.name, false)

    fun setOnboardingStatus(status: Boolean) =
        prefs.edit().putBoolean(SharedPrefsKeys.ONBOARDING_STATUS.name, status).apply()


    companion object {
        private const val PREFERENCES_FILE_NAME = "redi_shared_prefs"
    }
}