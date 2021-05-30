package controllers.chat.models

import com.google.gson.annotations.SerializedName

data class EditMessageData(
    @SerializedName("chatName")
    val chatName: String,
    @SerializedName("messageId")
    val messageId: Int,
    @SerializedName("text")
    val text: String
)
