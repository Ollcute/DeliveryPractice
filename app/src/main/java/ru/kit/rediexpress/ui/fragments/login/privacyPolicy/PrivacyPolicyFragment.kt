package ru.kit.rediexpress.ui.fragments.login.privacyPolicy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.kit.rediexpress.R
import ru.kit.rediexpress.databinding.FragmentPrivacyPolicyBinding
import ru.kit.rediexpress.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [PrivacyPolicyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PrivacyPolicyFragment : BaseFragment<FragmentPrivacyPolicyBinding, PrivacyPolicyViewModel>(
    FragmentPrivacyPolicyBinding::inflate
) {
    override val viewModel: PrivacyPolicyViewModel by viewModel()

    override fun initView() {
        binding.apply {

        }
    }

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

}