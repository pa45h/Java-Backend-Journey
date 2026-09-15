

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.junit.with.junit5.ReverseString;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReverseStringTest {
	
	ReverseStringTest(){
		System.out.println("Instance is created!!");
	}
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("*****Runs before all test cases*****");
	}
	
	@BeforeEach
	void init() {
		System.out.println("Runs before every test cases..!");
	}

	@Test
	void test() {
		ReverseString rs = new ReverseString();
		assertEquals("abc", rs.reverseString("cba"), "Wrong o/p");
	}
	
	@Test
	void testForArray() {
		int[] expectedArr = {1,2,3,4,5};
		int[] actualArr = {1,2,3,4,5};
		
		assertArrayEquals(expectedArr, actualArr, ()->"Wrong Array");
		
		ReverseString rs = new ReverseString();
		assertThrows(Exception.class, ()->rs.reverseString(null));
	}
	
	public static void arrSort() {
		int[] arr = {9,8,7,6,5,4,3,2,1,0};
		for(int i=0; i<1000000; i++) {
			Arrays.sort(arr);
			}
	}
	
	@Test
	void testPerformance() {
		assertTimeout(Duration.ofMillis(10), ()->arrSort());
	}
	
	@AfterEach
	void destroy() {
		System.out.println("Runs After Every Test cases");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("*****Runs After All Test cases*****");
	}

}
