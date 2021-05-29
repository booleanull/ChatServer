package controllers.chat

import com.google.gson.Gson
import controllers.base.BaseController
import controllers.base.post
import controllers.base.responses.error.BadRequestErrorResponse
import controllers.base.responses.ok.BaseOkResponse
import controllers.chat.models.StartChatData
import repositories.token.TokenManager
import spark.Spark.webSocket

class ChatController(
    private val gson: Gson,
    private val tokenManager: TokenManager
) : BaseController {

    override fun start() {
        initChat()
        initStartChat()
    }

    private fun initChat() {
        webSocket("/chat", ChatWebSocketHandler(gson, tokenManager))
    }

    private fun initStartChat() {
        post("/chat/start", { req, res ->
            val data = gson.fromJson(req.body(), StartChatData::class.java)
            if (data.login == null) {
                throw BadRequestErrorResponse.halt(gson)
            }

            BaseOkResponse()
        }, gson::toJson, tokenManager)
    }
}