package ru.kit.rediexpress.ui.activity.login

import androidx.navigation.fragment.NavHostFragment
import ru.kit.rediexpress.R
import ru.kit.rediexpress.databinding.ActivityLoginBinding
import ru.kit.rediexpress.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(
    ActivityLoginBinding::inflate
) {
    override val viewModel: LoginViewModel by viewModel()

    override fun initView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(R.id.splashFragment)
    }

    override fun observeViewModel() {}
}