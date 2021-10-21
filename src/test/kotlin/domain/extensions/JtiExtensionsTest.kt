package domain.extensions

import domain.entities.Jti
import domain.exceptions.DuplicateJtiException
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class JtiExtensionsTest {
    private val timeExpiredLimit = 1440L
    private val timeExpired = 1441L
    private val timeNotExpired = 1439L


    @Test
    fun `given createdAt value is expired with value limit should return a JTI object`() {

        val request = Jti("ID-01", " CLI-01", LocalDateTime.now().minusMinutes(timeExpiredLimit))
        val response = request.isExpired24Time()

        Assertions.assertThat(response).isInstanceOf(Jti::class.java)
    }

    @Test
    fun `given createdAt value is expired should return a JTI object`() {

        val request = Jti("ID-01", " CLI-01", LocalDateTime.now().minusMinutes(timeExpired))
        val response = request.isExpired24Time()

        Assertions.assertThat(response).isInstanceOf(Jti::class.java)
    }

    @Test
    fun `given createdAt value is NOT expired should return an exception DuplicateJtiException`() {

        val request = Jti("ID-01", " CLI-01", LocalDateTime.now().minusMinutes( timeNotExpired))
        val thrown: Throwable = Assertions.catchThrowable { request.isExpired24Time() }

        Assertions.assertThat(thrown).isInstanceOf(DuplicateJtiException::class.java)
    }
}
