package domain.services.impl

import domain.entities.Jti
import domain.extensions.isExpired24Time
import domain.repository.JtiRepository
import domain.services.JtiService


class JtiServiceImpl(private val jtiRepository: JtiRepository): JtiService {

    override fun isAlreadyRegistered(jti: Jti): Jti {

        return when (val listOfJti = retrieve(jti)) {
            emptyList<Jti>() -> insert(jti)
            else -> handleDuplicate(listOfJti, jti)
        }
    }

    private fun handleDuplicate(listOfJti: List<Jti>, jti: Jti): Jti {

        listOfJti.forEach { it.isExpired24Time().deleteJti() }
        insert(jti)

        return jti
    }

    private fun insert(jti: Jti) = jtiRepository.insert(jti)
    private fun retrieve(jti: Jti) = jtiRepository.get(jti.data)
    private fun Jti.deleteJti() = jtiRepository.delete(this.data)
}




