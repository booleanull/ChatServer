package databases.chat.models

import databases.base.HibEntity
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "table_messages", schema = "public")
class HibMessage: Serializable, HibEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    var authorId: Int = 0
    var text: String = ""
    var time: String = ""
}