package repositories.chat.models

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("id")
    val id: Int,
    @SerializedName("authorId")
    val authorId: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("time")
    val time: String
)