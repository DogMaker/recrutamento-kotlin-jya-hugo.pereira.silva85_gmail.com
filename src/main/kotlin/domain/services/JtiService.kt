package domain.services

import domain.entities.Jti

interface JtiService {
    fun isAlreadyRegistered(jti: Jti): Jti
}