package databases

import databases.chat.models.HibChat
import databases.chat.models.HibMessage
import databases.user.models.HibUser
import repositories.chat.models.Chat
import repositories.chat.models.Message
import repositories.user.models.User

fun User.toHibUser() = HibUser().also { hib ->
    hib.id = id
    hib.token = token
    hib.login = login
    hib.name = name
    hib.photo = photo
    hib.password = password
    hib.chats = chats.map { it.toHibChat() }
}

fun HibUser.toUser() = User(id, token, login, name, photo, password, chats.map { it.toChat() })

fun Chat.toHibChat() = HibChat().also { hib ->
    hib.id = id
    hib.name = name
    hib.message = messages.map { it.toHibMessage() }
}

fun HibChat.toChat() = Chat(id, name, message.map { it.toMessage() })

fun Message.toHibMessage() = HibMessage().also { hib ->
    hib.id = id
    hib.authorId = authorId
    hib.text = text
    hib.time = time
}

fun HibMessage.toMessage() = Message(id, authorId, text, time)