package com.batuhan.testing.TestDrivenDevelopment;

public class FizzBuzz {

    public static String compute(int i) {
        StringBuilder result = new StringBuilder();

        if (i % 3 == 0) {
            result.append("Fizz");
        }
        if (i % 5 == 0) {
            result.append("Buzz");
        }
        if (result.isEmpty()) {
            result.append(i);
        }
        return result.toString();
    }

    /*
    public static String compute(int i) {
        if ((i % 3 == 0) && (i % 5 == 0)) {
            return "FizzBuzz"; // If number is divisible by 3 and 5, print FizzBuzz.
        } else if (i % 3 == 0) {
            return "Fizz"; // If number is divisible by 3, print Fizz.
        } else if (i % 5 == 0) {
            return "Buzz"; // If number is divisible by 5, print Buzz.
        } else {
            return Integer.toString(i); // If number is Not divisible by 3 or 5, then print the number.
        }
    }
    */
}