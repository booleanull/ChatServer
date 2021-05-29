package repositories.chat.models

import repositories.user.models.User

data class Message(
    val id: Int,
    val chatId: Int,
    val author: User,
    val text: String,
    val time: String
)