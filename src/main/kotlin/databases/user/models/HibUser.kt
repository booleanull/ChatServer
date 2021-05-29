package databases.user.models

import databases.base.HibEntity
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
    var photo: String = ""
    var password: String = ""
}
