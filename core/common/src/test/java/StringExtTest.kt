import com.paranid5.star_wars_travel.core.common.domain.utils.toIntOrZero
import com.paranid5.star_wars_travel.core.common.domain.utils.toLongOrZero
import org.junit.jupiter.api.Test

class StringExtTest {
    @Test
    fun toNumberOrZeroText() {
        assert("123".toIntOrZero() == 123)
        assert("123".toLongOrZero() == 123L)

        assert("-123".toIntOrZero() == -123)
        assert("-123".toLongOrZero() == -123L)

        assert("123F".toIntOrZero() == 0)
        assert("123F".toLongOrZero() == 0L)

        assert("bebra".toIntOrZero() == 0)
        assert("bebra".toLongOrZero() == 0L)
    }
}