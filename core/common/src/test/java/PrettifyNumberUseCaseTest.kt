import com.paranid5.star_wars_travel.core.common.domain.use_case.prettifyNumber
import org.junit.jupiter.api.Test

class PrettifyNumberUseCaseTest  {
    @Test
    fun prettifyNumberTest() {
        assert(prettifyNumber("1234") == "1 234")
        assert(prettifyNumber("1000000") == "1 000 000")
        assert(prettifyNumber("123") == "123")
        assert(prettifyNumber("BEBRA") == "BEBRA")
        assert(prettifyNumber("BEBRA1234_5678") == "BEBRA1 234_5 678")
    }
}