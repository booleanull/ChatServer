package controllers.chat.models

import com.google.gson.annotations.SerializedName

data class RemoveMessageData(
    @SerializedName("chatName")
    val chatName: String,
    @SerializedName("messageId")
    val messageId: Int
)
