package utils;

public final class TestUtil {
    private TestUtil() {}

    private static final String RESET  = "\u001B[0m";
    private static final String RED    = "\u001B[31m";
    private static final String GREEN  = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";

    /**
     * Run a test comparing expected and received values.
     * Prints green "[PASS] <name>" when they match, otherwise prints red "[FAIL] <name>"
     * and shows expected/received in yellow.
     */
    public static void run(String name, Object expected, Object received) {
        boolean passed = java.util.Objects.equals(expected, received);
        if (passed) {
            System.out.println(GREEN + "[PASS] " + name + RESET);
        } else {
            System.out.println(RED + "[FAIL] " + name + RESET);
            System.out.println(YELLOW + "  expected: " + String.valueOf(expected) + RESET);
            System.out.println(YELLOW + "  received: " + String.valueOf(received) + RESET);
        }
    }

    /**
     * Convenience overload for boolean conditions.
     */
    public static void run(String name, boolean condition) {
        if (condition) {
            System.out.println(GREEN + "[PASS] " + name + RESET);
        } else {
            System.out.println(RED + "[FAIL] " + name + RESET);
        }
    }

    /**
     * Run the test and throw AssertionError on failure (useful in automated runs).
     */
    public static void require(String name, Object expected, Object received) {
        run(name, expected, received);
        if (!java.util.Objects.equals(expected, received)) {
            throw new AssertionError("Test failed: " + name + " expected=" + expected + " received=" + received);
        }
    }
}
