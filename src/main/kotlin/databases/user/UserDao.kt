package databases.user

import controllers.auth.models.AuthData
import databases.base.BaseDao
import databases.user.models.HibUser
import org.hibernate.SessionFactory

class UserDao(sessionFactory: SessionFactory) : BaseDao<HibUser>(sessionFactory, HibUser::class.java) {

    fun userWithDataExists(user: HibUser): Boolean {
        val login = user.login

        return withEntityManager({
            it.createQuery(
                "FROM HibUser " +
                        "WHERE login=:login", HibUser::class.java
            )
                .setParameter("login", login)
                .resultList
                .isNotEmpty()
        }) ?: true
    }

    fun findUser(id: Int): HibUser? {
        val result = withEntityManager({
            it.createQuery(
                "FROM HibUser " +
                        "WHERE id=:id", HibUser::class.java
            )
                .setParameter("id", id)
                .resultList
                .toList()
        }) ?: emptyList()

        return result.firstOrNull()
    }

    fun findUser(token: String): HibUser? {
        val result = withEntityManager({
            it.createQuery(
                "FROM HibUser " +
                        "WHERE token=:token", HibUser::class.java
            )
                .setParameter("token", token)
                .resultList
                .toList()
        }) ?: emptyList()

        return result.firstOrNull()
    }

    fun findUser(authData: AuthData): HibUser? {
        val result = withEntityManager({
            it.createQuery(
                "FROM HibUser " +
                        "WHERE login=:login AND password=:password", HibUser::class.java
            )
                .setParameter("login", authData.login)
                .setParameter("password", authData.password)
                .resultList
                .toList()
        }) ?: emptyList()

        return result.firstOrNull()
    }

    fun findUserByLogin(login: String): HibUser? {
        val result = withEntityManager({
            it.createQuery(
                "FROM HibUser " +
                        "WHERE login=:login", HibUser::class.java
            )
                .setParameter("login", login)
                .resultList
                .toList()
        }) ?: emptyList()

        return result.firstOrNull()
    }
}