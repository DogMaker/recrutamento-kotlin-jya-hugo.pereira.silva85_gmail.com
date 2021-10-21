package repository

import domain.entities.Jti
import domain.extensions.TWENTY_FOUR_HOURS_IN_MINUTES
import domain.repository.JtiRepository
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import repository.schemas.JtiSchema
import java.time.LocalDateTime

class JtiRepositoryImpl: JtiRepository {


    override fun get(jti: String): List<Jti> {
       return transaction {
           JtiSchema.select { JtiSchema.data eq jti }
            .map { JtiSchema.toJti(it)  }
           .toList()
       }
    }

    override fun insert(jti: Jti): Jti {
        transaction {
            JtiSchema.insert{ row ->
                row[JtiSchema.data] = jti.data
                row[JtiSchema.clientId] = jti.clientId
                row[JtiSchema.createdAt] = jti.createdAt
            }
        }
        return jti
    }

    override fun delete(jti: String): Int{
        return transaction {
            JtiSchema.deleteWhere {
                JtiSchema.createdAt.between(
                    LocalDateTime.now().minusHours(TWENTY_FOUR_HOURS_IN_MINUTES),
                    LocalDateTime.now()
                )
            }
        }
    }
}

