package controllers.chat.models

import com.google.gson.annotations.SerializedName

data class ChatData(
    @SerializedName("chatName")
    val chatName: String
)