import org.eclipse.jetty.http.HttpStatus
import org.junit.jupiter.api.Test

class ConsentValidationTest {

    @Test
    fun `given that a new Jti is requested should sucessfully insert`() {
        post(jtiSample(), HttpStatus.CREATED_201)
    }

    @Test
    fun `given that a client inform an existing Jti, than should return an error`() {
        post(jtiSample(), HttpStatus.BAD_REQUEST_400)
    }

    @Test
    fun `given that a client inform an existing Jti expired in data, than should sucessfully insert`() {
        post(jtiSample(), HttpStatus.CREATED_201)
    }
}