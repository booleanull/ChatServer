package databases.chat.models

import databases.base.HibEntity
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "table_chats", schema = "public")
class HibChat: Serializable, HibEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    var name: String = ""
    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    var message: List<HibMessage> = listOf()
}