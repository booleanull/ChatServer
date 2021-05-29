package repositories.user

import controllers.auth.models.AuthData
import databases.toHibUser
import databases.toUser
import databases.user.UserDao
import repositories.user.models.User

class UserRepositoryImpl(private val userDao: UserDao): UserRepository {

    override fun createUser(user: User): Int {
        val hibUser = user.toHibUser()
        if(userDao.userWithDataExists(hibUser)) {
            return -1
        }

        return userDao.create(hibUser)
    }

    override fun getUser(authData: AuthData): User? {
        return userDao.findUser(authData)?.toUser()
    }

    override fun getUserByToken(token: String): User? {
        return userDao.findUser(token)?.toUser()
    }

    override fun getUserByLogin(login: String): User? {
        return userDao.findUserByLogin(login)?.toUser()
    }

    override fun saveUser(user: User): User? {
        return userDao.update(user.toHibUser())?.toUser()
    }

    override fun removeUser(user: User) {
        userDao.delete(user.toHibUser())
    }
}