package controllers.chat.response.ok

import com.google.gson.annotations.SerializedName
import controllers.base.responses.ok.BaseOkResponse
import repositories.chat.models.Chat

data class ChatsOkResponse(
    @SerializedName("chats")
    val chats: List<String>
): BaseOkResponse()