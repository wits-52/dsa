package solutions;

import java.util.HashSet;
import java.util.Set;

import utils.PerfUtil;
import utils.TestUtil;

public class RepeatedNTimes {
    private int solveWithLinearTimeAndMemoryComplexity(int[] nums) {
        Set<Integer> existing = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (existing.contains(nums[i])) {
                return nums[i];
            }
            existing.add(nums[i]);
        }
        return -1;
    }
    private boolean doesGivenIndexExistInPreviousThree(int[] nums, int idx) {
        for (int i = idx - 1; i >= Math.max(0, idx - 3); i--) {
            if (nums[idx] == nums[i]) {
                return true;
            }
        }

        return false;
    }
    private int solveWithLinearTimeAndConstantMemoryComplexity(int[] nums) {
        int n = nums.length;
        if (n % 2 == 1 || n < 4) return -1; // According to problem constraints it is not possible though.

        for (int i = 1; i < nums.length; i++) {
            if (this.doesGivenIndexExistInPreviousThree(nums, i)) {
                return nums[i];
            }
        }

        return -1;
    }
    public int repeatedNTimes(int[] nums) {
        return this.solveWithLinearTimeAndConstantMemoryComplexity(nums);
    }
    public int repeatedNTimes(int[] nums, boolean useLinearTimeAndMemoryComplexity) {
        if (useLinearTimeAndMemoryComplexity) {
            return this.solveWithLinearTimeAndMemoryComplexity(nums);
        } else {
            return this.solveWithLinearTimeAndConstantMemoryComplexity(nums);
        }
    }
    public static void main(String[] args) {
        RepeatedNTimes solution = new RepeatedNTimes();

        TestUtil.run("Test Case #1", 3, solution.repeatedNTimes(new int[]{1, 2, 3, 3}));
        TestUtil.run("Test Case #2", 2, solution.repeatedNTimes(new int[]{2, 1, 2, 5, 3, 2}));
        TestUtil.run("Test Case #3", 5, solution.repeatedNTimes(new int[]{5, 1, 5, 2, 5, 3, 5, 4}));
        TestUtil.run("Test Case #4", 9, solution.repeatedNTimes(new int[]{9, 5, 6, 9}));
        TestUtil.run("Test Case #5", 2, solution.repeatedNTimes(new int[]{2, 6, 2, 1}));

        // with PerfUtil
        try {
            int n = 10000;
            int[] nums = new int[2 * n];

            for (int i = 0; i < n; i++) {
                nums[i] = i;
                nums[n + i] = n;
            }
            System.out.println("PerfUtil Result using constant memory: ");
            PerfUtil.run(() -> solution.repeatedNTimes(nums), true);
            System.out.println("PerfUtil Result using HashSet: ");
            PerfUtil.run(() -> solution.repeatedNTimes(nums, true), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
