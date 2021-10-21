package domain.repository

import domain.entities.Jti

interface JtiRepository{
    fun get(jti: String): List<Jti>
    fun insert(jti: Jti): Jti
    fun delete(jti: String): Int
    fun deleteAllExpiredJti(): Int
}