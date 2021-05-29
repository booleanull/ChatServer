package repositories.chat

import repositories.chat.models.Chat

interface ChatRepository {

    fun getChats(): List<Chat>
}