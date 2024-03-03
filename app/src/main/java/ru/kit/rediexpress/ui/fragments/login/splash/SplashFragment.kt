package ru.kit.rediexpress.ui.fragments.login.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ru.kit.rediexpress.R
import ru.kit.rediexpress.databinding.FragmentSplashBinding
import ru.kit.rediexpress.domain.DomainResult
import ru.kit.rediexpress.ui.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(
    FragmentSplashBinding::inflate
) {
    override val viewModel: SplashViewModel by viewModel()

    override fun initView() {
        viewModel.getOnboardingStatus()
    }

    override fun observeViewModel() {
        viewModel.apply {
            onboardingStatus.observe {
                when (it) {
                    is DomainResult.Error -> {}
                    is DomainResult.Loading -> {}
                    is DomainResult.Success -> {
                        if (it.data) {
                            findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
                        } else {
                            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnboardingFragment())
                        }
                    }
                }
            }
        }
    }

}