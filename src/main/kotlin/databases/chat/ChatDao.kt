package databases.chat

import databases.base.BaseDao
import databases.chat.models.HibChat
import org.hibernate.SessionFactory

class ChatDao(sessionFactory: SessionFactory) : BaseDao<HibChat>(sessionFactory, HibChat::class.java) {

    fun findChatByName(name: String): HibChat? {
        val result = withEntityManager({
            it.createQuery(
                "FROM HibChat " +
                        "WHERE name=:name", HibChat::class.java
            )
                .setParameter("name", name)
                .resultList
                .toList()
        }) ?: emptyList()

        return result.firstOrNull()
    }
}