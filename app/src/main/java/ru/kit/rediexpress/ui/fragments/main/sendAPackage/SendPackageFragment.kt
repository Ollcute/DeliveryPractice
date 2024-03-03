package ru.kit.rediexpress.ui.fragments.main.sendAPackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.kit.rediexpress.R
import ru.kit.rediexpress.databinding.FragmentSendPackageBinding
import ru.kit.rediexpress.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [SendPackageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SendPackageFragment : BaseFragment<FragmentSendPackageBinding, SendPackageViewModel>(
    FragmentSendPackageBinding::inflate
) {
    override val viewModel: SendPackageViewModel by viewModel()

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun observeViewModel() {
        TODO("Not yet implemented")
    }

}