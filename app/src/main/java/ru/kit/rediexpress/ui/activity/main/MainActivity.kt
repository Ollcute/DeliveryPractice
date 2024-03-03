package ru.kit.rediexpress.ui.activity.main

import android.os.Bundle
import androidx.core.view.isVisible
import ru.kit.rediexpress.R
import ru.kit.rediexpress.databinding.ActivityMainBinding
import ru.kit.rediexpress.ui.base.BaseActivity
import ru.kit.rediexpress.utils.setupWithNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    ActivityMainBinding::inflate
) {
    override val viewModel: MainViewModel by viewModel()

    override fun initView() {}

    override fun observeViewModel() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.home,
            R.navigation.wallet,
            R.navigation.track,
            R.navigation.profile
        )

        binding.bottomNav.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment_activity_main,
            intent = intent,
            this,
            listOf(binding.selectedHome, binding.selectedWallet, binding.selectedTrack, binding.selectedProfile)
        )
    }

    fun showBottomBar() {
        binding.bottomNav.isVisible = true
    }

    fun hideBottomBar() {
        binding.bottomNav.isVisible = false
    }
}