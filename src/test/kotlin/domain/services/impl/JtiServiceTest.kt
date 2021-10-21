package domain.services.impl

import domain.entities.Jti
import domain.exceptions.DuplicateJtiException
import domain.repository.JtiRepository
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


class JtiServiceTest {
    private val jtiRepository = mockk<JtiRepository>(relaxed = true)
    private val jtiServiceImpl = JtiServiceImpl(jtiRepository)


    @Test
    fun `given that create a Jti without exist any id in DB, should return the same Jti object`(){
        val jti = Jti("ID-01", " CLI-01", LocalDateTime.now())

        every { jtiRepository.get(jti.data) } returns listOf()
        every { jtiRepository.insert(jti) } returns jti

        val request = jtiServiceImpl.isAlreadyRegistered(jti)

        assertThat(request).isEqualTo(jti)
    }

    @Test
    fun `given that create a Jti with the same id expired in DB, should return the same Jti object`(){
        val jti = Jti("ID-01", " CLI-01", LocalDateTime.now())
        val jtiReturned = Jti("ID-01", " CLI-01", LocalDateTime.now().minusMinutes(1441))

        every { jtiRepository.get(jti.data) } returns listOf(jtiReturned)
        every { jtiRepository.delete(jti.data) } returns 1
        every { jtiRepository.insert(jti) } returns jti

        val request = jtiServiceImpl.isAlreadyRegistered(jti)

        assertThat(request).isEqualTo(jti)
    }

    @Test
    fun `given that there are more than one Jti in the db with the same id and one is expired, should return an DuplicateJtiException`(){
        val jti = Jti("ID-01", " CLI-01", LocalDateTime.now())
        val jtiReturned = Jti("ID-01", " CLI-01", LocalDateTime.now().minusMinutes(1439))

        every { jtiRepository.get(jti.data) } returns listOf(jtiReturned, jti)
        every { jtiRepository.delete(jti.data) } returns 1
        every { jtiRepository.insert(jti) } returns jti

        val thrown: Throwable = Assertions.catchThrowable { jtiServiceImpl.isAlreadyRegistered(jti) }

        assertThat(thrown).isInstanceOf(DuplicateJtiException::class.java)
    }

    @Test
    fun `given that there are many Jti in the db all expired, should return the same Jti object`(){
        val timeNotExpired = 1440L
        val timeNotExpired2 = 1442L

        val jti = Jti("ID-01", " CLI-01", LocalDateTime.now())
        val jtiReturned = Jti("ID-01", " CLI-01", LocalDateTime.now().minusMinutes(timeNotExpired))
        val jtiReturned2 = Jti("ID-01", " CLI-01", LocalDateTime.now().minusMinutes(timeNotExpired2))

        every { jtiRepository.get(jti.data) } returns listOf(jtiReturned, jtiReturned2)
        every { jtiRepository.delete(jti.data) } returns 2
        every { jtiRepository.insert(jti) } returns jti

       val response = jtiServiceImpl.isAlreadyRegistered(jti)

        assertThat(response).isEqualTo(jti)
    }
}

