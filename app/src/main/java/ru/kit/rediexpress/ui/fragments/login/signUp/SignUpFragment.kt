package ru.kit.rediexpress.ui.fragments.login.signUp

import android.content.Intent
import android.text.InputType
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.kit.rediexpress.R
import ru.kit.rediexpress.databinding.FragmentSignUpBinding
import ru.kit.rediexpress.ui.activity.main.MainActivity
import ru.kit.rediexpress.ui.base.BaseFragment
import ru.kit.rediexpress.ui.base.error_dialog.ErrorDialog
import ru.kit.rediexpress.ui.base.error_dialog.ErrorDialogParams
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>(
    FragmentSignUpBinding::inflate
) {
    override val viewModel: SignUpViewModel by viewModel()

    override fun initView() {
        binding.apply {
            val spannableString =
                SpannableString(getString(R.string.text_already_have_an_account_sign_in))
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)

                    ds.color = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
                    ds.isUnderlineText = false
                }
            }
            spannableString.setSpan(
                clickableSpan,
                24,
                spannableString.length,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE
            )
            tvAlreadyHaveAnAccount.movementMethod = LinkMovementMethod()
            tvAlreadyHaveAnAccount.text = spannableString

            val termsSpannableString =
                SpannableString(getString(R.string.text_by_ticking_this_box_you_agree_to_our_terms_and_conditions_and_private_policy))
            val termsClickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    findNavController().navigate(R.id.action_signUpFragment_to_privacyPolicyFragment)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)

                    ds.color = ContextCompat.getColor(requireContext(), R.color.colorSecondary)
                    ds.isUnderlineText = false
                }
            }
            termsSpannableString.setSpan(
                termsClickableSpan,
                37,
                termsSpannableString.length,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE
            )
            tvTermsOfPolicy.movementMethod = LinkMovementMethod()
            tvTermsOfPolicy.text = termsSpannableString

            cbRemember.setOnCheckedChangeListener { _, isChecked ->
                btnSignUp.isEnabled = isChecked
            }

            btnSignUp.setOnClickListener {
                viewModel.signUp(
                    fullname = etFullName.text.toString(),
                    phone = etPhone.text.toString(),
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString(),
                    repeatPassword = etPassword.text.toString()
                )

//                if (viewModel.checkEmail(etEmail.text.toString())) {
//                    if (cbRemember.isChecked) {
//                        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
//                    } else {
//                        val dialog =
//                            ErrorDialog.getInstance(ErrorDialogParams("Для регистрации требуется согласиться с условиями политики конфидециальности"))
//                        dialog.show(childFragmentManager, ErrorDialog.TAG)
//                    }
//                } else {
//                    val dialog =
//                        ErrorDialog.getInstance(ErrorDialogParams("Некорректный адрес электронной почты"))
//                    dialog.show(childFragmentManager, ErrorDialog.TAG)
//                }
            }

            btnViewPassword.setOnClickListener {
                if (etPassword.transformationMethod != null)
                    etPassword.transformationMethod = null
                else
                    etPassword.transformationMethod = PasswordTransformationMethod()
            }

            btnGoogle.setOnClickListener {
                //TODO register from google
            }
        }
    }

    override fun observeViewModel() {
        viewModel.apply {

        }
    }
}