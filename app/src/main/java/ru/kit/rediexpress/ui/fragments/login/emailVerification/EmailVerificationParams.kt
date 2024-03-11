package ru.kit.rediexpress.ui.fragments.login.emailVerification

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmailVerificationParams (
    val fullName: String,
    val email: String,
    val phone: String,
): Parcelable