package controllers.auth

import com.google.gson.Gson
import controllers.auth.models.AuthData
import controllers.auth.models.RegData
import controllers.auth.responses.error.AuthDataErrorResponse
import controllers.auth.responses.error.UserAlreadyExistsErrorResponse
import controllers.auth.responses.ok.AuthorisationOkResponse
import controllers.auth.responses.ok.RegistrationOkResponse
import controllers.base.BaseController
import controllers.base.post
import controllers.base.responses.error.BadRequestErrorResponse
import controllers.base.responses.ok.BaseOkResponse
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import repositories.token.TokenManager
import repositories.user.UserRepository
import repositories.user.model.User

class AuthController(
    private val gson: Gson,
    private val userRepository: UserRepository,
    private val tokenManager: TokenManager
) : BaseController {

    override fun start() {
        initAuth()
        initRegistration()
    }

    private fun initAuth() {
        post("/auth", { req, res ->
            val data = gson.fromJson(req.body(), AuthData::class.java)
            if (data.login == null || data.password == null) {
                    throw BadRequestErrorResponse.halt(gson)
            }

            val password = String(Hex.encodeHex(DigestUtils.md5(data.password)))
            val authData = AuthData(data.login, password)
            val user = userRepository.getUser(authData) ?: throw AuthDataErrorResponse.halt(gson)

            val token = tokenManager.generateAuthToken(authData)
            val updateUser = User(user.id, token, user.login, user.name, user.photo, user.password)
            val savedUser = userRepository.saveUser(updateUser)!!

            AuthorisationOkResponse(savedUser.id, savedUser.login, savedUser.name, savedUser.photo, savedUser.token)
        }, gson::toJson)
    }

    private fun initRegistration() {
        post("/reg", { req, res ->
            val data = gson.fromJson(req.body(), RegData::class.java)
            if (data.login == null || data.password == null || data.name == null || data.photo == null) {
                throw BadRequestErrorResponse.halt(gson)
            }

            val password = String(Hex.encodeHex(DigestUtils.md5(data.password)))
            val authData = AuthData(data.login, password)
            val token = tokenManager.generateAuthToken(authData)
            val user = User(0, token, data.login, data.name, data.photo, password)

            val id = userRepository.createUser(user)
            if (id == -1) throw UserAlreadyExistsErrorResponse.halt(gson)
            RegistrationOkResponse(id, user.login, user.name, user.photo, user.token)
        }, gson::toJson)
    }
}