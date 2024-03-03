package ru.kit.rediexpress.ui.fragments.login.newPassword

import android.content.Intent
import androidx.fragment.app.Fragment
import ru.kit.rediexpress.databinding.FragmentNewPasswordBinding
import ru.kit.rediexpress.ui.activity.main.MainActivity
import ru.kit.rediexpress.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [NewPasswordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewPasswordFragment : BaseFragment<FragmentNewPasswordBinding, NewPasswordViewModel>(
    FragmentNewPasswordBinding::inflate
) {
    override val viewModel: NewPasswordViewModel by viewModel()

    override fun initView() {
        binding.apply {
            btnLogIn.setOnClickListener {
                if (!etNewPassword.text.isNullOrBlank() && !etConfirmPassword.text.isNullOrBlank() && etNewPassword.text == etConfirmPassword.text) {
                    //TODO update password and login
                    startMainActivity()
                }
            }
        }
    }

    override fun observeViewModel() {
        binding.apply {

        }
    }

    private fun startMainActivity() {
        val i = Intent(requireContext(), MainActivity::class.java)
        startActivity(i)
    }
}