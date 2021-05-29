package repositories.user.models

import com.google.gson.annotations.SerializedName
import repositories.chat.models.Chat

data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("token")
    val token: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("chats")
    val chats: List<Chat>
)
