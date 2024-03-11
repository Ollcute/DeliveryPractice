package ru.kit.rediexpress.ui.fragments.login.emailVerification

import android.content.Intent
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.SessionStatus
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import ru.kit.rediexpress.R
import ru.kit.rediexpress.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kit.rediexpress.databinding.FragmentEmailVerificationBinding
import ru.kit.rediexpress.domain.SharedPref
import ru.kit.rediexpress.domain.models.InsertProfileModel
import ru.kit.rediexpress.domain.supabase.supabase
import ru.kit.rediexpress.ui.activity.main.MainActivity

/**
 * A simple [Fragment] subclass.
 * Use the [EmailVerificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EmailVerificationFragment : BaseFragment<FragmentEmailVerificationBinding, EmailVerificationViewModel>(
    FragmentEmailVerificationBinding::inflate
) {
    override val viewModel: EmailVerificationViewModel by viewModel()

    private val params: EmailVerificationParams by lazy {
        arguments?.getParcelable("email", EmailVerificationParams::class.java) ?:
            throw IllegalArgumentException("не передан аргумент")
    }

    override fun initView() {
        binding.apply {
            val spannableString = SpannableString(getString(R.string.text_if_you_didn_t_receive_code_resend))
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    //TODO addResendingCode
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)

                    ds.color = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
                    ds.isUnderlineText = false
                }
            }
            spannableString.setSpan(clickableSpan, 27, spannableString.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            tvDidnTReceiveCode.movementMethod = LinkMovementMethod()
            tvDidnTReceiveCode.text = spannableString

            btnSetConfirmEmail.setOnClickListener {
                lifecycleScope.launch {
                    supabase.auth.verifyEmailOtp(
                        type = OtpType.Email.EMAIL,
                        email = params.email,
                        token = binding.firstPinView.text.toString(),
                    )
                }
            }
        }

        lifecycleScope.launch {
            supabase.auth.sessionStatus.collect {
                when(it) {
                    is SessionStatus.Authenticated -> {
                        lifecycleScope.launch {
                            supabase.from("profiles").insert(
                                InsertProfileModel(
                                    full_name = params.fullName,
                                    phone_number = params.phone,
                                    email_address = params.email
                                )
                            )
                            SharedPref(requireContext()).email = params.email
                            startMainActivity()
                        }
                    }
                    SessionStatus.LoadingFromStorage -> println("Loading from storage")
                    SessionStatus.NetworkError -> println("Network error")
                    SessionStatus.NotAuthenticated -> println("Not authenticated")
                }
            }
        }
    }

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    private fun startMainActivity() {
        val i = Intent(requireContext(), MainActivity::class.java)
        startActivity(i)
    }

}