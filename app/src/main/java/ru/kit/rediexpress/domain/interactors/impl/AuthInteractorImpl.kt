package ru.kit.rediexpress.domain.interactors.impl

import android.se.omapi.Session
import android.text.TextUtils
import ru.kit.rediexpress.data.model.ErrorType
import ru.kit.rediexpress.data.repository.SharedPrefsRepository
import ru.kit.rediexpress.domain.DomainResult
import ru.kit.rediexpress.domain.interactors.AuthInteractor
import ru.kit.rediexpress.domain.supabase.supabase
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class AuthInteractorImpl(private val sharedPrefs: SharedPrefsRepository): AuthInteractor {

    override val sessionStatus: SharedFlow<SessionStatus>
        get() = supabase.auth.sessionStatus

    override suspend fun createAccount(
        fullname: String,
        phone: String,
        email: String,
        password: String,
        repeatPassword: String
    ): DomainResult<Boolean> {
        if (password != repeatPassword)
            return DomainResult.Error(
                type = ErrorType.REQUEST_ERROR,
                details = "Введённые пароли не совпадают"
            )
        else if (!checkEmail(email))
            return DomainResult.Error(
                type = ErrorType.REQUEST_ERROR,
                details = "Неверный формат почты"
            )


        val user = supabase.auth.signUpWith(Email) {
            this.email = email
            this.password = password

            this.data = buildJsonObject {
                put("fullname", fullname)
                put("phone", phone)
            }
        }

//        supabase.auth.verifyEmailOtp()



        return if (user != null)
            DomainResult.Success(true)
        else
            DomainResult.Error(
                type = ErrorType.REQUEST_ERROR,
                details = "Введённые пароли не совпадают"
            )
    }


    override suspend fun login(
        email: String,
        password: String
    ) {
        supabase.auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }



    fun checkEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}