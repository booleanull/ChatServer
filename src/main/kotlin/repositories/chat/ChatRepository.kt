package repositories.chat

import repositories.chat.models.Chat

interface ChatRepository {

    fun getChat(chatName: String): Chat?

    fun saveChat(chat: Chat): Chat?
}