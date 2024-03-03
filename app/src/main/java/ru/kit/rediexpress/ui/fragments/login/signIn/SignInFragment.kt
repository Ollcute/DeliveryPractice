package ru.kit.rediexpress.ui.fragments.login.signIn

import android.content.Intent
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ru.kit.rediexpress.R
import ru.kit.rediexpress.databinding.FragmentSignInBinding
import ru.kit.rediexpress.ui.activity.main.MainActivity
import ru.kit.rediexpress.ui.base.BaseFragment
import ru.kit.rediexpress.ui.base.error_dialog.ErrorDialog
import ru.kit.rediexpress.ui.base.error_dialog.ErrorDialogParams
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : BaseFragment<FragmentSignInBinding, SignInViewModel>(
    FragmentSignInBinding::inflate
) {
    override val viewModel: SignInViewModel by viewModel()


    override fun observeViewModel(): Unit = with(viewModel) {
        lifecycleScope.launch {
            event.collect { event ->
                when(event) {
                    SignInEvent.OnSuccessSignIn -> startMainActivity()
                }
            }
        }
    }


    override fun initView() {
        binding.apply {
            val spannableString = SpannableString(getString(R.string.text_don_t_have_an_account_sign_up))
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)

                    ds.color = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
                    ds.isUnderlineText = false
                }
            }
            spannableString.setSpan(clickableSpan, 22, spannableString.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            tvDontHaveAnAccount.movementMethod = LinkMovementMethod()
            tvDontHaveAnAccount.text = spannableString

            tvForgotPassword.setOnClickListener {
                findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
            }

            btnSignIn.setOnClickListener {
                viewModel.login(
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
            }

            btnViewPassword.setOnClickListener {
                if (etPassword.transformationMethod != null)
                    etPassword.transformationMethod = null
                else
                    etPassword.transformationMethod = PasswordTransformationMethod()
            }

            btnGoogle.setOnClickListener {
                //TODO google authorization
            }
        }
    }

    private fun startMainActivity() {
        val i = Intent(requireContext(), MainActivity::class.java)
        startActivity(i)
    }


}