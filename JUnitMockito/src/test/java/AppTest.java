import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AppTest {
	
	@Mock
	PaymentService ps;

	@Test
	void test() {
		App ap = new App();
		when(ps.getToken()).thenReturn(20);
		assertEquals(20, ap.getReciept(ps));
	}

}
