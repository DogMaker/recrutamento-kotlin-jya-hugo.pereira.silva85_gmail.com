package domain.services.impl

import domain.entities.Jti
import domain.extensions.isHigherThan24Hours
import domain.services.JtiService
import repository.JtiRepositoryImpl

class JtiServiceImpl(private val jtiRepository: JtiRepositoryImpl): JtiService {

      override fun isAlreadyRegistered(jti: Jti): Jti {

        return when(val listOfJti = retrieve(jti)){
            null -> insert(jti)
            else -> handleDuplicate(listOfJti, jti)
        }
    }

    private fun handleDuplicate(listOfJti: List<Jti>?, jti: Jti): Jti {

        listOfJti?.forEach{  it.createdAt.isHigherThan24Hours() }
        insert(jti)

        return jti
    }

    private fun insert(jti: Jti) = jtiRepository.insert(jti)
    private fun retrieve(jti: Jti) = jtiRepository.get(jti.data)
}

