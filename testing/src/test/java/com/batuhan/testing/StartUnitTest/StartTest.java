package com.batuhan.testing.StartUnitTest;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // It initializes the Spring Boot application with its full context and is used for integration tests.
// @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) // Creates a more readable test name by replacing the underscores in the test method name with spaces.
// @DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class) // Creates a meaningful test name by formatting the name of the test method as a sentence.
// @DisplayNameGeneration(DisplayNameGenerator.Simple.class) // It uses the name of the test method as it is, without any modification or special formatting.

// @TestMethodOrder(MethodOrderer.DisplayName.class) // it allows tests to be run in alphabetical order by view name.
// @TestMethodOrder(MethodOrderer.MethodName.class) // it allows tests to be run in alphabetical order according to method names.
// @TestMethodOrder(MethodOrderer.Random.class) // it allows tests to be run in a random order.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // it ensures that tests are run in the  order specified by the @Order annotation.
class StartTest {
	Start startTest;

	@BeforeEach // Defines the method that runs once before each test.
	void setupBeforeEach() {
		// set up
		startTest = new Start();
		System.out.println("@BeforeEach executes before the execution of each test method.");
	}

	@AfterEach // Defines the method that runs once after each test.
	void tearDownAfterEach() {
		System.out.println("Running @AfterEach.\n");
	}

	@BeforeAll // Defines a method, usually static, that runs once before all tests.
	static void setupBeforeEachClass() {
		System.out.println("@BeforeAll executes only once before all test methods execution in the class.\n");
	}

	@AfterAll // Defines a method, usually static, that runs once after all tests.
	static void tearDownAfterAll() {
		System.out.println("@AfterAll executes only once after all test methods execution in the class.");
	}

	@Test // Marks the method to be tested and allows it to be executed by the test runner.
	@DisplayName("Equals and Not Equals") // Used to add readable annotations to tests.
	@Order(2) // specifies the order in which a test method is executed.
	// void test_Equals_And_Not_Equals()
	void testEqualsAndNotEquals() {
		System.out.println("Running test: testEqualsAndNotEquals.");
		int excepted = 14;
		int unexcepted = 12;

		/*	// execute
			int actual = startTest.addNumbers(6, 8);

			// assert
			assertEquals(excepted, actual, "6+8 must be 14.");
			assertNotEquals(unexcepted, actual, "6+8 must not be 12.");	*/

		// execute and assert
		assertEquals(14, startTest.addNumbers(6,8), "6+8 must be 14.");
		assertNotEquals(12, startTest.addNumbers(7, 4), "7+4 must bot be 12.");
	}

	@Test
	@DisplayName("Multiply")
	void testMultiply() {
		System.out.println("Running test: testMultiply.");

		// execute and assert
		assertEquals(12, startTest.multiplyNumbers(4,3), "4*3 must be 12");
	}

	@Test
	@DisplayName("Null and Not Null")
	@Order(1)
	// void test_Null_And_Not_Null()
	void testNullAndNotNull() {
		System.out.println("Running test: testNullAndNotNull.");
		String str1 = null;
		String str2 = "Batuhan";

		// execute and assert
		assertNull(startTest.checkNull(str1), "Object should be null.");
		assertNotNull(startTest.checkNull(str2), "Object should not be null.");
	}

	@Test
	@DisplayName("Same and Not Same")
	@Order(5)
	// void test_Same_And_Not_Same()
	void testSameAndNotSame() {
		System.out.println("Running test: testSameAndNotSame.");
		String str = "Batu";

		// execute and assert
		assertSame(startTest.getName(), startTest.getNameDuplicate(), "Object should refer to same object.");
		assertNotSame(str, startTest.getName(), "Object should not refer to same object.");
	}

	@Test
	@DisplayName("True and False")
	@Order(-4)
	// void test_True_And_False()
	void testTrueAndFalse() {
		System.out.println("Running test: testTrueAndFalse.");
		int gradeOne = 14;
		int gradeTwo = 8;

		// execute and assert
		assertTrue(startTest.isGreater(gradeOne, gradeTwo), "This should return true.");
		assertFalse(startTest.isGreater(gradeTwo, gradeOne), "This should return false.");
	}

	@Test
	@DisplayName("Array Equals")
	// void test_Array_Equals()
	void testArrayEquals() {
		System.out.println("Running test: testArrayEquals.");
		String[] stringArray = {"A", "B", "C"};

		// execute and assert
		assertArrayEquals(stringArray, startTest.getAlphabet(), "Arrays should be the same.");
	}

	@Test
	@DisplayName("Iterable equals")
	// void test_Iterable_Equals()
	void testIterableEquals() {
		System.out.println("Running test: testIterableEquals.");
		List<String> theList = List.of("Batu", "han", "Baysal");

		// execute and assert
		assertIterableEquals(theList, startTest.getNameInList(), "Excepted list should be same as actual list.");
	}

	@Test
	@DisplayName("Lines match")
	// void test_Lines_Match()
	void testLinesMatch() {
		System.out.println("Running test: testLinesMatch.");
		List<String> theList = List.of("Batu", "han", "Baysal");

		// execute and assert
		assertLinesMatch(theList, startTest.getNameInList(), "Lines should match.");
	}

	@Test
	@DisplayName("Throws and Does Not Throw")
	// void test_Throws_And_Does_Not_Throw()
	void testThrowsAndDoesNotThrow() {
		System.out.println("Running test: testThrowsAndDoesNotThrow.");

		// execute and assert
		// Verifies that an exception to the specified type has been thrown.
		assertThrows(Exception.class, () -> { startTest.throwException(-1); }, "Should throw exception.");
		// It confirms that no exceptions were thrown.
		assertDoesNotThrow(() -> { startTest.throwException(5); }, "Should not throw exception.");
	}

	@Test
	@DisplayName("Timeout")
	// void test_Timeout()
	void testTimeout() {
		System.out.println("Running test: testTimeout.");

		// execute and assert
		// Verifies that the test is completed without exceeding the specified time limit, and immediately interrupts the test if the time limit is exceeded.
		assertTimeoutPreemptively(Duration.ofSeconds(3), () -> { startTest.checkTimeout(); }, "Method should execute in 3 seconds.");
	}
}