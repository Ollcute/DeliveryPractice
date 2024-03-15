package ru.kit.rediexpress.ui.fragments.main.chats.list

import androidx.recyclerview.widget.RecyclerView
import ru.kit.rediexpress.databinding.ChatItemBinding

class ChatViewHolder(
    private val binding: ChatItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(details: ChatDetails) = with(binding) {
        tvChatName.text = details.username
        tvChatLastMessage.text = details.lastMessage

        return@with
    }
}