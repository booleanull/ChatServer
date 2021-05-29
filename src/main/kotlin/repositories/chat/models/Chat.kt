package repositories.chat.models

import com.google.gson.annotations.SerializedName

data class Chat(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("messages")
    val messages: List<Message>
)