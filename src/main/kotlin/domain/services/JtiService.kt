package domain.services

import domain.entities.Jti
import repository.JtiRepositoryImpl

class JtiService(private val jtiRepository: JtiRepositoryImpl) {
    fun validate(jti: Jti): Jti {
        jtiRepository.insert(jti.data)

        return jti
    }
}