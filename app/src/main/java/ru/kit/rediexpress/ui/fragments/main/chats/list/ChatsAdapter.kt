package ru.kit.rediexpress.ui.fragments.main.chats.list

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.kit.rediexpress.databinding.ChatItemBinding
import ru.kit.rediexpress.utils.recyclerview.DefaultListAdapter

class ChatsAdapter: DefaultListAdapter<ChatDetails, ChatViewHolder>(
    areItemsTheSameCompare = { oldItem, newItem ->
        oldItem.username == newItem.username
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ChatViewHolder(
            binding = ChatItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
        )

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}