package ru.kit.rediexpress.di

import ru.kit.rediexpress.ui.activity.login.LoginViewModel
import ru.kit.rediexpress.ui.activity.main.MainViewModel
import ru.kit.rediexpress.ui.fragments.login.forgotPassword.ForgotPasswordFragment
import ru.kit.rediexpress.ui.fragments.login.forgotPassword.ForgotPasswordViewModel
import ru.kit.rediexpress.ui.fragments.login.newPassword.NewPasswordViewModel
import ru.kit.rediexpress.ui.fragments.login.onboarding.OnboardingViewModel
import ru.kit.rediexpress.ui.fragments.login.otpVerification.OTPVerificationViewModel
import ru.kit.rediexpress.ui.fragments.login.privacyPolicy.PrivacyPolicyViewModel
import ru.kit.rediexpress.ui.fragments.login.signIn.SignInViewModel
import ru.kit.rediexpress.ui.fragments.login.signUp.SignUpFragment
import ru.kit.rediexpress.ui.fragments.login.signUp.SignUpViewModel
import ru.kit.rediexpress.ui.fragments.login.splash.SplashViewModel
import ru.kit.rediexpress.ui.fragments.main.home.HomeViewModel
import ru.kit.rediexpress.ui.fragments.main.profile.ProfileViewModel
import ru.kit.rediexpress.ui.fragments.main.track.TrackViewModel
import ru.kit.rediexpress.ui.fragments.main.wallet.WalletViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    viewModel {
        LoginViewModel()
    }

    viewModel {
        MainViewModel()
    }

    viewModel {
        SplashViewModel(
            startInteractor = get()
        )
    }

    viewModel {
        OnboardingViewModel(
            startInteractor = get()
        )
    }

    viewModel {
        SignInViewModel(
            authInteractor = get()
        )
    }

    viewModel {
        SignUpViewModel(
            authInteractor = get()
        )
    }

    viewModel {
        ForgotPasswordViewModel()
    }

    viewModel {
        OTPVerificationViewModel()
    }

    viewModel {
        NewPasswordViewModel()
    }

    viewModel {
        HomeViewModel()
    }

    viewModel {
        WalletViewModel()
    }

    viewModel {
        TrackViewModel()
    }

    viewModel {
        ProfileViewModel()
    }

    viewModel {
        PrivacyPolicyViewModel()
    }
}