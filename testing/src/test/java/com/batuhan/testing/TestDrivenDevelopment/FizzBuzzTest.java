package com.batuhan.testing.TestDrivenDevelopment;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FizzBuzzTest {
    // If number is divisible by 3, print Fizz.
    @Test
    @DisplayName("Divisible by Three")
    @Order(1)
    void testForDivisibleByThree() {
        String excepted = "Fizz";
        assertEquals(excepted, FizzBuzz.compute(3), "Should return Fizz.");
    }

    // If number is divisible by 5, print Buzz.
    @Test
    @DisplayName("Divisible by Five")
    @Order(2)
    void testForDivisibleByFive() {
        String excepted = "Buzz";
        assertEquals(excepted, FizzBuzz.compute(5), "Should return Buzz.");
    }

    // If number is divisible by 3 and 5, print FizzBuzz.
    @Test
    @DisplayName("Divisible by Three and Five")
    @Order(3)
    void testForDivisibleByThreeAndFive() {
        String excepted = "FizzBuzz";
        assertEquals(excepted, FizzBuzz.compute(15), "Should return FizzBuzz.");
    }

    // If number is NOT divisible by 3 or 5, then print the number.
    @Test
    @DisplayName("Not Divisible by Three or Five")
    @Order(4)
    void testForNotDivisibleByThreeOrFive() {
        String excepted = "1";
        assertEquals(excepted, FizzBuzz.compute(1), "Should return 1.");
    }

    @ParameterizedTest(name = "value={0}, excepted={1}") // is an annotation used for parameterized tests. This annotation allows the test to run using the parameter values specified before the test is run.
    @CsvFileSource(resources = "/small-fizzbuzz-test-data.csv") // is an annotation used to read data from a CSV file for parametric testing.
    @DisplayName("Testing with Small FizzBuzz Data File")
    @Order(5)
    void testSmallFizzBuzzDataFile(int value, String excepted) {
        assertEquals(excepted, FizzBuzz.compute(value));
    }

    @ParameterizedTest(name = "value={0}, excepted={1}")
    @CsvFileSource(resources = "/medium-fizzbuzz-test-data.csv")
    @DisplayName("Testing with Medium FizzBuzz Data File")
    @Order(6)
    void testMediumFizzBuzzDataFile(int value, String excepted) {
        assertEquals(excepted, FizzBuzz.compute(value));
    }

    @ParameterizedTest(name = "value={0}, excepted={1}")
    @CsvFileSource(resources = "/large-fizzbuzz-test-data.csv")
    @DisplayName("Testing with Large FizzBuzz Data File")
    @Order(7)
    void testLargeFizzBuzzDataFile(int value, String excepted) {
        assertEquals(excepted, FizzBuzz.compute(value));
    }
}