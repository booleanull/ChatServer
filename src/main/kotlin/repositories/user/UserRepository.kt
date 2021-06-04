package repositories.user

import controllers.auth.models.AuthData
import repositories.chat.models.Chat
import repositories.user.models.User

interface UserRepository {

    fun createUser(user: User): Int

    fun getUser(authData: AuthData): User?

    fun getUserById(id: Int): User?

    fun getUserByToken(token: String): User?

    fun getUserByLogin(login: String): User?

    fun getAllUsers(): List<User>

    fun saveUser(user: User): User?

    fun removeUser(user: User)
}