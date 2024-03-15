package ru.kit.rediexpress.ui.fragments.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import ru.kit.rediexpress.R
import ru.kit.rediexpress.databinding.FragmentHomeBinding
import ru.kit.rediexpress.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kit.rediexpress.domain.SharedPref
import ru.kit.rediexpress.domain.models.SelectProfileModel
import ru.kit.rediexpress.domain.supabase.supabase

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel: HomeViewModel by viewModel()

    override fun initView(): Unit = with(binding) {
        btnCustomerCare.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                tvCustomerServiceTitle.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
            } else {
                tvCustomerServiceTitle.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.colorPrimary)
            }
        }

        btnBookRider.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_chatsFragment)
        }

        lifecycleScope.launch {
            val email = SharedPref(requireContext()).email.lowercase()
            val profile = supabase.from("profiles").select {
                filter {
                    eq("email_address", email)
                }
            }.decodeList<SelectProfileModel>().first()

            tvHelloUser.text = "Hello, ${profile.full_name}!"
        }


    }

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

}