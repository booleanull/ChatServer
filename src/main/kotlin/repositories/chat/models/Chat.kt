package repositories.chat.models

import repositories.user.models.User

data class Chat(
    val id: Int,
    val users: List<User>,
    val messages: List<Message>
)