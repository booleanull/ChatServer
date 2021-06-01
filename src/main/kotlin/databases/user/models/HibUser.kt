package databases.user.models

import databases.base.HibEntity
import databases.chat.models.HibChat
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "table_users", schema = "public")
class HibUser: Serializable, HibEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    var token: String = ""
    var login: String = ""
    var name: String = ""
    @Column(columnDefinition = "text")
    var photo: String = ""
    var password: String = ""
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @OrderBy("id")
    var chats: List<HibChat> = listOf()
}
