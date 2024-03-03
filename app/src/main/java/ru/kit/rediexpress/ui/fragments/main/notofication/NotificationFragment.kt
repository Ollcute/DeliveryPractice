package ru.kit.rediexpress.ui.fragments.main.notofication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.kit.rediexpress.R
import ru.kit.rediexpress.databinding.FragmentNotificationBinding
import ru.kit.rediexpress.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationFragment : BaseFragment<FragmentNotificationBinding, NotificationViewModel>(
    FragmentNotificationBinding::inflate
) {
    override val viewModel: NotificationViewModel by viewModel()

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun observeViewModel() {
        TODO("Not yet implemented")
    }

}