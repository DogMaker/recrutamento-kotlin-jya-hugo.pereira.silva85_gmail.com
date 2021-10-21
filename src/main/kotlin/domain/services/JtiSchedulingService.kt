package domain.services


interface JtiSchedulingService {
    fun deleteAllExpiredJti(): Int
}