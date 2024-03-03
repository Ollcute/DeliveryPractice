package ru.kit.rediexpress.ui.fragments.login.onboarding

import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import ru.kit.rediexpress.R
import ru.kit.rediexpress.databinding.FragmentOnboardingBinding
import ru.kit.rediexpress.domain.DomainResult
import ru.kit.rediexpress.ui.base.BaseFragment
import ru.kit.rediexpress.ui.fragments.login.onboarding.pager.OnboardingPagerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *
 *
 *
 */
class OnboardingFragment : BaseFragment<FragmentOnboardingBinding, OnboardingViewModel>(
    FragmentOnboardingBinding::inflate
) {
    override val viewModel: OnboardingViewModel by viewModel()
    private var signInOrUp = 0

    override fun initView() {
        binding.apply {
            vpOnboarding.adapter =
                OnboardingPagerAdapter(this@OnboardingFragment, viewModel.getOnboardingData())

            TabLayoutMediator(
                tlOnboarding,
                vpOnboarding,
                true
            ) { _, _ -> }.attach()

            btnSkip.setOnClickListener {
                signInOrUp = 0
                viewModel.changeOnboardingStatus()
            }

            btnNext.setOnClickListener {
                vpOnboarding.currentItem = vpOnboarding.currentItem + 1
            }

            btnSignUp.setOnClickListener {
                signInOrUp = 1
                viewModel.changeOnboardingStatus()
            }

            val spannableString = SpannableString(getString(R.string.text_already_have_an_account_sign_in))
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    signInOrUp = 0
                    viewModel.changeOnboardingStatus()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)

                    ds.color = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
                    ds.isUnderlineText = false
                }
            }
            spannableString.setSpan(clickableSpan, 24, spannableString.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            tvAlreadyHaveAnAccount.movementMethod = LinkMovementMethod()
            tvAlreadyHaveAnAccount.text = spannableString

            vpOnboarding.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    viewModel.changePage(position)
                }
            })
        }
    }

    override fun observeViewModel() {
        viewModel.apply {
            isEnd.observe { isEnd ->
                if (isEnd) {
                    binding.btnSignUp.isVisible = true
                    binding.tvAlreadyHaveAnAccount.isVisible = true
                    binding.btnSkip.isVisible = false
                    binding.btnNext.isVisible = false
                } else {
                    binding.btnSignUp.isVisible = false
                    binding.tvAlreadyHaveAnAccount.isVisible = false
                    binding.btnSkip.isVisible = true
                    binding.btnNext.isVisible = true
                }
            }
            onboardingStatus.observe { result ->
                when (result) {
                    is DomainResult.Error -> {}
                    is DomainResult.Loading -> {}
                    is DomainResult.Success -> {
                        when (signInOrUp) {
                            0 -> findNavController().navigate(R.id.action_onboardingFragment_to_signInFragment)
                            else -> findNavController().navigate(R.id.action_onboardingFragment_to_signUpFragment)
                        }
                    }
                }
            }
        }
    }

}
