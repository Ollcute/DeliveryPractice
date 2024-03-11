package ru.kit.rediexpress.ui.fragments.main.profile

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.gotrue.SignOutScope
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import ru.kit.rediexpress.databinding.FragmentProfileBinding
import ru.kit.rediexpress.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kit.rediexpress.domain.SharedPref
import ru.kit.rediexpress.domain.models.SelectProfileModel
import ru.kit.rediexpress.domain.supabase.supabase
import ru.kit.rediexpress.ui.activity.login.LoginActivity
import ru.kit.rediexpress.ui.activity.main.MainActivity

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(
    FragmentProfileBinding::inflate
) {
    override val viewModel: ProfileViewModel by viewModel()

    override fun initView(): Unit = with(binding) {
        val email = SharedPref(requireContext()).email.lowercase()

        lifecycleScope.launch {
            val profile = supabase.from("profiles").select {
                filter {
                    eq("email_address", email)
                }
            }.decodeList<SelectProfileModel>().first()

            tvFullName.text = profile.full_name
            tvEmail.text = profile.email_address
            tvPhone.text = profile.phone_number
        }

        fprofileBtnLogOut.setOnClickListener {
            lifecycleScope.launch {
                supabase.auth.signOut(
                    SignOutScope.LOCAL
                )
                startLoginActivity()
            }
        }
    }

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

    private fun startLoginActivity() {
        val i = Intent(requireContext(), LoginActivity::class.java)
        startActivity(i)
    }

}