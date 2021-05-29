package controllers.profile

import com.google.gson.Gson
import controllers.base.BaseController
import controllers.base.get
import controllers.base.post
import controllers.base.responses.error.UserNotFoundErrorResponse
import controllers.profile.model.EditData
import controllers.profile.responses.ok.EditOkResponse
import controllers.profile.responses.ok.ProfileReceivedOkResponse
import repositories.token.TokenManager
import repositories.user.UserRepository
import repositories.user.models.User

class ProfileController (
    private val gson: Gson,
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager
) : BaseController {

    override fun start() {
        initGetProfile()
        initEdit()
    }

    private fun initGetProfile() {
        get("/profile/:id", { req, res ->
            val id = req.params(":id").toInt()

            val user = userRepository.getUserById(id) ?: throw UserNotFoundErrorResponse.halt(gson)

            ProfileReceivedOkResponse(user.id, user.login, user.name, user.photo)
        }, gson::toJson, tokenManager)
    }

    private fun initEdit() {
        post("/profile/edit", { req, res ->
            val data = gson.fromJson(req.body(), EditData::class.java)
            val token = req.headers("token")

            val user = userRepository.getUserByToken(token)!!

            val updatedName = data.name.ifEmpty { null }
            val updatedPhoto = data.photo.ifEmpty { null }

            val updatedUser = User(
                user.id,
                user.token,
                user.login,
                updatedName ?: user.name,
                updatedPhoto ?: user.photo,
                user.password,
                user.chats
            )

            userRepository.saveUser(updatedUser)

            EditOkResponse(updatedUser.id, updatedUser.login, updatedUser.name, updatedUser.photo)
        }, gson::toJson, tokenManager)
    }
}