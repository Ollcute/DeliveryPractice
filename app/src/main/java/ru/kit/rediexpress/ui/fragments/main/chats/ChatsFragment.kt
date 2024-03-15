package ru.kit.rediexpress.ui.fragments.main.chats


import androidx.navigation.fragment.findNavController
import ru.kit.rediexpress.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kit.rediexpress.databinding.FragmentChatsBinding

class ChatsFragment : BaseFragment<FragmentChatsBinding, ChatsViewModel>(
    FragmentChatsBinding::inflate
) {
    override val viewModel: ChatsViewModel by viewModel()

    override fun initView(): Unit = with(binding) {
        fchatsBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

}