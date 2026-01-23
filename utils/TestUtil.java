package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class TestUtil {
    private TestUtil() {}

    private static final String RESET  = "\u001B[0m";
    private static final String RED    = "\u001B[31m";
    private static final String GREEN  = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN   = "\u001B[36m";

    // Global state for .only() mode
    private static boolean onlyMode = false;
    private static final Set<String> onlyTests = new HashSet<>();

    public static final Skip skip = new Skip();
    public static final Only only = new Only();

    /**
     * Run a test comparing expected and received values.
     * Prints green "[PASS] <name>" when they match, otherwise prints red "[FAIL] <name>"
     * and shows expected/received in yellow.
     */
    public static void run(String name, Object expected, Object received) {
        if (onlyMode && !onlyTests.contains(name)) {
            System.out.println(CYAN + "[SKIP] " + name + RESET);
            return;
        }
        boolean passed = java.util.Objects.equals(expected, received);
        if (passed) {
            System.out.println(GREEN + "[PASS] " + name + RESET);
        } else {
            System.out.println(RED + "[FAIL] " + name + RESET);
            System.out.println(YELLOW + "  expected: " + String.valueOf(expected) + RESET);
            System.out.println(YELLOW + "  received: " + String.valueOf(received) + RESET);
        }
    }
    public static void run(String name, int[] expected, int[] received) {
        if (onlyMode && !onlyTests.contains(name)) {
            System.out.println(CYAN + "[SKIP] " + name + RESET);
            return;
        }
        boolean passed = ArrayUtil.equals(expected, received);
        if (passed) {
            System.out.println(GREEN + "[PASS] " + name + RESET);
        } else {
            System.out.println(RED + "[FAIL] " + name + RESET);
            // TODO: fix toString
            System.out.println(YELLOW + "  expected: " + String.valueOf(expected.toString()) + RESET);
            System.out.println(YELLOW + "  received: " + String.valueOf(received.toString()) + RESET);
        }
    }
    public static void run(String name, int[][] expected, int[][] received) {
        if (onlyMode && !onlyTests.contains(name)) {
            System.out.println(CYAN + "[SKIP] " + name + RESET);
            return;
        }
        boolean passed = MatrixUtil.equals(expected, received);
        if (passed) {
            System.out.println(GREEN + "[PASS] " + name + RESET);
        } else {
            System.out.println(RED + "[FAIL] " + name + RESET);
            // TODO: fix toString
            System.out.println(YELLOW + "  expected: " + String.valueOf(expected.toString()) + RESET);
            System.out.println(YELLOW + "  received: " + String.valueOf(received.toString()) + RESET);
        }
    }

    /**
     * Convenience overload for boolean conditions.
     */
    public static void run(String name, boolean condition) {
        if (onlyMode && !onlyTests.contains(name)) {
            System.out.println(CYAN + "[SKIP] " + name + RESET);
            return;
        }
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

    /**
     * Skip class provides methods to skip test cases.
     * Usage: TestUtil.skip.run(name, expected, received)
     */
    public static class Skip {
        public void run(String name, Object expected, Object received) {
            System.out.println(CYAN + "[SKIP] " + name + RESET);
        }

        public void run(String name, int[] expected, int[] received) {
            System.out.println(CYAN + "[SKIP] " + name + RESET);
        }

        public void run(String name, int[][] expected, int[][] received) {
            System.out.println(CYAN + "[SKIP] " + name + RESET);
        }

        public void run(String name, boolean condition) {
            System.out.println(CYAN + "[SKIP] " + name + RESET);
        }

        public void require(String name, Object expected, Object received) {
            System.out.println(CYAN + "[SKIP] " + name + RESET);
        }
    }

    /**
     * Only class provides methods to run only specific test cases.
     * When any test is marked with .only, all other tests will be skipped.
     * Usage: TestUtil.only.run(name, expected, received)
     */
    public static class Only {
        public void run(String name, Object expected, Object received) {
            onlyMode = true;
            onlyTests.add(name);
            TestUtil.run(name, expected, received);
        }

        public void run(String name, int[] expected, int[] received) {
            onlyMode = true;
            onlyTests.add(name);
            TestUtil.run(name, expected, received);
        }

        public void run(String name, int[][] expected, int[][] received) {
            onlyMode = true;
            onlyTests.add(name);
            TestUtil.run(name, expected, received);
        }

        public void run(String name, boolean condition) {
            onlyMode = true;
            onlyTests.add(name);
            TestUtil.run(name, condition);
        }

        public void require(String name, Object expected, Object received) {
            onlyMode = true;
            onlyTests.add(name);
            TestUtil.require(name, expected, received);
        }
    }

    /**
     * TestSuite class to register and run tests with proper .only() and .skip() support.
     * This ensures that if any test is marked with .only(), all other tests are skipped.
     * 
     * Usage:
     * TestSuite suite = new TestSuite();
     * suite.test("name1", expected, received);
     * suite.skip("name2", expected, received);
     * suite.only("name3", expected, received);
     * suite.run();
     */
    public static class TestSuite {
        private List<TestStep> steps = new ArrayList<>();
        private boolean hasOnlyTests = false;

        private static class TestStep {
            String name;
            Runnable action;
            boolean skip;
            boolean only;

            TestStep(String name, Runnable action, boolean skip, boolean only) {
                this.name = name;
                this.action = action;
                this.skip = skip;
                this.only = only;
            }
        }

        /**
         * Register a normal test.
         */
        public void test(String name, Object expected, Object received) {
            steps.add(new TestStep(name, () -> TestUtil.run(name, expected, received), false, false));
        }

        public void test(String name, int[] expected, int[] received) {
            steps.add(new TestStep(name, () -> TestUtil.run(name, expected, received), false, false));
        }

        public void test(String name, int[][] expected, int[][] received) {
            steps.add(new TestStep(name, () -> TestUtil.run(name, expected, received), false, false));
        }

        public void test(String name, boolean condition) {
            steps.add(new TestStep(name, () -> TestUtil.run(name, condition), false, false));
        }

        /**
         * Register a test to be skipped.
         */
        public void skip(String name, Object expected, Object received) {
            steps.add(new TestStep(name, () -> TestUtil.run(name, expected, received), true, false));
        }

        public void skip(String name, int[] expected, int[] received) {
            steps.add(new TestStep(name, () -> TestUtil.run(name, expected, received), true, false));
        }

        public void skip(String name, int[][] expected, int[][] received) {
            steps.add(new TestStep(name, () -> TestUtil.run(name, expected, received), true, false));
        }

        public void skip(String name, boolean condition) {
            steps.add(new TestStep(name, () -> TestUtil.run(name, condition), true, false));
        }

        /**
         * Register a test to be run exclusively. If any test is marked with .only(),
         * all other non-.only() tests will be skipped.
         */
        public void only(String name, Object expected, Object received) {
            hasOnlyTests = true;
            steps.add(new TestStep(name, () -> TestUtil.run(name, expected, received), false, true));
        }

        public void only(String name, int[] expected, int[] received) {
            hasOnlyTests = true;
            steps.add(new TestStep(name, () -> TestUtil.run(name, expected, received), false, true));
        }

        public void only(String name, int[][] expected, int[][] received) {
            hasOnlyTests = true;
            steps.add(new TestStep(name, () -> TestUtil.run(name, expected, received), false, true));
        }

        public void only(String name, boolean condition) {
            hasOnlyTests = true;
            steps.add(new TestStep(name, () -> TestUtil.run(name, condition), false, true));
        }

        /**
         * Execute all registered tests.
         * Respects .skip() and .only() markers.
         */
        public void run() {
            for (TestStep step : steps) {
                if (step.skip) {
                    System.out.println(CYAN + "[SKIP] " + step.name + RESET);
                } else if (hasOnlyTests && !step.only) {
                    System.out.println(CYAN + "[SKIP] " + step.name + RESET);
                } else {
                    step.action.run();
                }
            }
        }
    }
}
