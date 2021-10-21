package repository

import Logger
import domain.entities.Jti
import domain.exceptions.DuplicateJtiException
import domain.extensions.FOURTY_EIGHT_IN_MINUTES
import domain.extensions.TWENTY_FOUR_HOURS_IN_MINUTES
import domain.repository.JtiRepository
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.postgresql.util.PSQLException
import repository.exception.DatabaseInsertionException
import repository.schemas.JtiSchema
import java.sql.SQLException
import java.time.LocalDateTime
import org.jetbrains.exposed.sql.statements.InsertStatement

class JtiRepositoryImpl : JtiRepository {
    //append a heritage from c6utils
    private val logger = Logger

    override fun get(jti: String): List<Jti> {
        return transaction {
            JtiSchema.select { JtiSchema.data eq jti }
                .map { JtiSchema.toJti(it) }
                .toList()
        }
    }

    override fun insert(jti: Jti): Jti {
        logger.info(LogTags.CREATE_JTI) { "Insert a new jti into DB $jti" }

        try {
            transaction {
                JtiSchema.insert { row ->
                    row[JtiSchema.data] = jti.data
                    row[JtiSchema.clientId] = jti.clientId
                    row[JtiSchema.createdAt] = jti.createdAt
                }
            }
        } catch (exception: SQLException) {
            when ((exception as? ExposedSQLException)?.cause) {
                is PSQLException -> throw DuplicateJtiException()
                    .also {
                        logger.error(LogTags.DUPLICATE_JTI_EXCEPTION) { DuplicateJtiException().message }
                    }
                else -> throw DatabaseInsertionException()
                    .also {
                        logger.error(LogTags.DATABASE_EXCEPTION) { exception.message.toString() }
                    }
            }
        }

        return jti
    }

    override fun delete(jti: String): Int {
        logger.info(LogTags.DELETE_JI) { "Delete specific Jti expired" }

        return transaction {
            JtiSchema.deleteWhere {
                JtiSchema.data eq jti
            }
        }
    }

    override fun deleteAllExpiredJti(jti: String): Int {
        logger.info(LogTags.DELETE_JI) { "Delete all Jti expired records" }

        return transaction {
            JtiSchema.deleteWhere {
                JtiSchema.createdAt.between(
                    LocalDateTime.now().minusMinutes(FOURTY_EIGHT_IN_MINUTES),
                    LocalDateTime.now().minusMinutes(TWENTY_FOUR_HOURS_IN_MINUTES)
                )
            }
        }
    }
}



