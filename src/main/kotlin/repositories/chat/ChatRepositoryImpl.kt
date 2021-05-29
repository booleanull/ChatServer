package repositories.chat

import databases.chat.ChatDao
import databases.toChat
import databases.toHibChat
import repositories.chat.models.Chat

class ChatRepositoryImpl(
    private val chatDao: ChatDao
): ChatRepository {

    override fun getChat(chatName: String): Chat? {
        return chatDao.findChatByName(chatName)?.toChat()
    }

    override fun saveChat(chat: Chat): Chat? {
        return chatDao.update(chat.toHibChat())?.toChat()
    }
}