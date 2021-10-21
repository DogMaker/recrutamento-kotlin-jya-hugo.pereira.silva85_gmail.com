package repository.schemas

import domain.entities.Jti
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import rest.configuration.jtiRepository
import java.time.LocalDateTime

const val DATA_LENGTH = 26
const val CLIENT_LENGTH = 26

object JtiSchema: Table(name = "jti") {

    val data = varchar("data", DATA_LENGTH).primaryKey()
    val clientId = varchar("client_id", CLIENT_LENGTH)
    val createdAt = datetime("created_at")


    fun toJti(row: ResultRow): Jti {
        return Jti(
            data = row[data],
            clientId = row[clientId],
            createdAt = LocalDateTime.now()
        )
    }
}






