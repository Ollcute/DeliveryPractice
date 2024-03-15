package ru.kit.rediexpress.ui.fragments.main.chats

import androidx.navigation.fragment.findNavController
import ru.kit.rediexpress.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kit.rediexpress.databinding.FragmentChatsBinding
import ru.kit.rediexpress.ui.fragments.main.chats.list.ChatDetails
import ru.kit.rediexpress.ui.fragments.main.chats.list.ChatsAdapter

class ChatsFragment : BaseFragment<FragmentChatsBinding, ChatsViewModel>(
    FragmentChatsBinding::inflate
) {
    override val viewModel: ChatsViewModel by viewModel()

    private val chatsAdapter by lazy { ChatsAdapter() }

    private val chats = listOf(
        ChatDetails(username = "Ivan", lastMessage = "Hello World!"),
        ChatDetails(username = "Olga", lastMessage = "Deuchland"),
        ChatDetails(username = "Eugene", lastMessage = "NNN1314fw4fsefe"),
        ChatDetails(username = "Alex666", lastMessage = "Rummstein"),
    )

    override fun initView(): Unit = with(binding) {
        rvChats.adapter = chatsAdapter

        fchatsBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun observeViewModel() {
        viewModel.apply {

        }
    }

}