package tuf.sde_sheet.arrays_IV;

import java.util.HashMap;
import java.util.Map;

import utils.TestUtil;

public class LargestSubarrayWithZeroSum {
    public int maxLength(int arr[]) {
        Map<Integer, Integer> firstIndexForSum = new HashMap<>();
        firstIndexForSum.put(0, -1);

        int sum = 0, maxLength = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (!firstIndexForSum.containsKey(sum)) {
                firstIndexForSum.put(sum, i);
            } else {
                maxLength = Math.max(maxLength, i - firstIndexForSum.get(sum));
            }
        }

        return maxLength;
    }
    public static void main(String[] args) {
        LargestSubarrayWithZeroSum solution = new LargestSubarrayWithZeroSum();

        TestUtil.run("Test Case #1", 5, solution.maxLength(new int[]{15, -2, 2, -8, 1, 7, 10, 23}));
        TestUtil.run("Test Case #2", 0, solution.maxLength(new int[]{2, 10, 4}));
        TestUtil.run("Test Case #3", 5, solution.maxLength(new int[]{1, 0, -4, 3, 1, 0}));
        TestUtil.run("Test Case #4", 2, solution.maxLength(new int[]{1, -1}));
        TestUtil.run("Test Case #5", 6, solution.maxLength(new int[]{1, -1, 1, -1, 1, -1}));
        TestUtil.run("Test Case #6", 6, solution.maxLength(new int[]{9, 1, -1, 1, -1, 1, -1}));
    }
}
