package controllers.chat

import com.google.gson.Gson
import controllers.base.responses.error.TokenNotFoundErrorResponse
import org.eclipse.jetty.websocket.api.Session
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect
import org.eclipse.jetty.websocket.api.annotations.WebSocket
import repositories.token.TokenManager

@WebSocket
class ChatWebSocketHandler(
    private val gson: Gson,
    private val tokenManager: TokenManager
) {

    @OnWebSocketConnect
    fun onConnect(session: Session) {
        val token = session.upgradeRequest.getHeader("token") ?: ""
        if (!tokenManager.validateAuthToken(token)) {
            session.remote.sendString(gson.toJson(TokenNotFoundErrorResponse))
            session.disconnect()
        }
    }

    @OnWebSocketClose
    fun onClose(session: Session, statusCode: Int, reason: String) {

    }
}