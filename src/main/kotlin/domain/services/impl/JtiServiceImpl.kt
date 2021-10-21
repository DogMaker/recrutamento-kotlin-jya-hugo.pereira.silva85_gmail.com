package domain.services.impl

import domain.entities.Jti
import domain.extensions.isExpired24Time
import domain.services.JtiService
import repository.JtiRepositoryImpl


class JtiServiceImpl(private val jtiRepository: JtiRepositoryImpl): JtiService {

    override fun isAlreadyRegistered(jti: Jti): Jti {

        return when (val listOfJti = retrieve(jti)) {
            emptyList<Jti>() -> insert(jti)
            else -> handleDuplicate(listOfJti, jti)
        }
    }

    private fun handleDuplicate(listOfJti: List<Jti>?, jti: Jti): Jti {

        listOfJti?.forEach { jti ->
            jti.isExpired24Time().deleteJti() }
        insert(jti)

        return jti
    }

    private fun insert(jti: Jti) = jtiRepository.insert(jti)
    private fun retrieve(jti: Jti) = jtiRepository.get(jti.data)
    private fun Jti.deleteJti() = jtiRepository.delete(this.data)
}




