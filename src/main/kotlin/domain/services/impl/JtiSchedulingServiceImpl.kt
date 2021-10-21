package domain.services.impl


import domain.repository.JtiRepository
import domain.services.JtiSchedulingService

class JtiSchedulingServiceImpl(private val jtiRepository: JtiRepository): JtiSchedulingService {
    override fun deleteAllExpiredJti() = jtiRepository.deleteAllExpiredJti()
}