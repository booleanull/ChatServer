package databases

import databases.user.models.HibUser
import repositories.user.models.User

fun User.toHibUser() = HibUser().also { hib ->
    hib.id = id
    hib.token = token
    hib.login = login
    hib.name = name
    hib.photo = photo
    hib.password = password
}

fun HibUser.toUser() = User(id, token, login, name, photo, password)