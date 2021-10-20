package repository

import domain.entities.Jti
import domain.repository.JtiRepository
import repository.config.PostgresDBConfig

class JtiRepositoryImpl(private val conn: PostgresDBConfig) : JtiRepository {


    override fun get(jti: String): List<Jti> {
        TODO("Not yet implemented")
    }

    override fun insert(jti: String)= true

}

