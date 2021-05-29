package controllers.chat

import com.google.gson.Gson
import controllers.base.BaseController
import controllers.base.get
import controllers.base.post
import controllers.base.responses.error.BadRequestErrorResponse
import controllers.base.responses.ok.BaseOkResponse
import controllers.chat.models.ChatData
import controllers.chat.models.SendData
import controllers.chat.response.error.ChatNotFoundErrorResponse
import controllers.chat.response.error.ChatStartErrorResponse
import controllers.chat.response.ok.*
import repositories.chat.ChatRepository
import repositories.chat.models.Chat
import repositories.chat.models.Message
import repositories.token.TokenManager
import repositories.user.UserRepository
import repositories.user.models.User
import java.text.SimpleDateFormat
import java.util.*

class ChatController(
    private val gson: Gson,
    private val tokenManager: TokenManager,
    private val userRepository: UserRepository,
    private val chatRepository: ChatRepository
) : BaseController {

    override fun start() {
        initStartChat()
        initChats()
        initChat()
        initSend()
    }

    private fun initStartChat() {
        post("/chat/start", { req, res ->
            val data = gson.fromJson(req.body(), ChatData::class.java)
            if (data.chatName == null) {
                throw BadRequestErrorResponse.halt(gson)
            }

            val chat = chatRepository.getChat(data.chatName)
            val token = req.headers("token")!!
            val user = userRepository.getUserByToken(token)!!

            userRepository.saveUser(
                User(
                    user.id,
                    user.token,
                    user.login,
                    user.name,
                    user.photo,
                    user.password,
                    mutableListOf<Chat>().apply {
                        addAll(user.chats)
                        if (!user.chats.contains(chat)) {
                            add(chat ?: Chat(0, data.chatName, listOf()))
                        }
                    }
                )
            )

            val updateChat = chatRepository.getChat(data.chatName)!!
            val updateUser = userRepository.getUserByToken(token)!!

            if (updateUser.chats.contains(updateChat)) {
                getChatResponse(updateChat)
            } else {
                throw ChatStartErrorResponse.halt(gson)
            }
        }, gson::toJson, tokenManager)
    }

    private fun initChats() {
        get("/chats", { req, res ->
            val token = req.headers("token")!!
            val user = userRepository.getUserByToken(token)!!
            ChatsOkResponse(user.chats.map { it.name })
        }, gson::toJson, tokenManager)
    }

    private fun initChat() {
        post("/chat", { req, res ->
            val data = gson.fromJson(req.body(), ChatData::class.java)
            if (data.chatName == null) {
                throw BadRequestErrorResponse.halt(gson)
            }

            val chat = chatRepository.getChat(data.chatName) ?: throw ChatNotFoundErrorResponse.halt(gson)
            val token = req.headers("token")!!
            val user = userRepository.getUserByToken(token)!!

            if (user.chats.contains(chat)) {
                getChatResponse(chat)
            } else {
                throw ChatStartErrorResponse.halt(gson)
            }
        }, gson::toJson, tokenManager)
    }

    private fun initSend() {
        post("/chat/send", { req, res ->
            val data = gson.fromJson(req.body(), SendData::class.java)
            if (data.chatName == null || data.text == null) {
                throw BadRequestErrorResponse.halt(gson)
            }

            val token = req.headers("token")!!
            val user = userRepository.getUserByToken(token)!!
            val simpleDateFormat = SimpleDateFormat("hh:mm dd/MM/yyyy")
            val time = simpleDateFormat.format(Date(System.currentTimeMillis()))
            val message = Message(0, user.id, data.text, time)

            val chat = chatRepository.getChat(data.chatName)!!
            val updateChat = Chat(chat.id, chat.name, mutableListOf<Message>().apply {
                addAll(chat.messages)
                add(message)
            })
            chatRepository.saveChat(updateChat)

            BaseOkResponse()
        }, gson::toJson, tokenManager)
    }

    private fun getChatResponse(chat: Chat) =
        ChatOkResponse(
            ChatResponse(
                chat.id,
                chat.name,
                chat.messages.map { message ->
                    val user = userRepository.getUserById(message.authorId)!!
                    MessageResponse(
                        message.id,
                        UserResponse(user.id, user.login, user.name, user.photo),
                        message.text,
                        message.time
                    )
                }.reversed()
            )
        )
}