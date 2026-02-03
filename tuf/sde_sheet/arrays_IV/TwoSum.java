package tuf.sde_sheet.arrays_IV;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import utils.TestUtil;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> visited = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (visited.containsKey(target - nums[i])) {
                return new int[]{visited.get(target - nums[i]), i};
            }
            visited.put(nums[i], i);
        }

        return new int[]{};
    }
    public int[] twoSum(int[] nums, int target, boolean useConstMemory) {
        if (!useConstMemory) return this.twoSum(nums, target);

        Arrays.sort(nums);

        int start = 0, end = nums.length - 1;
        while (start < end) {
            int sum = nums[start] + nums[end];

            if (sum == target) {
                // Note: answer does not want index
                return new int[]{start, end};
            }
            if (sum > target) {
                end--;
            } else {
                start++;
            }
        }
        return new int[]{};
    }
    public static void main(String[] args) {
        TwoSum solution = new TwoSum();

        TestUtil.run("Test Case #1", new int[]{0, 1}, solution.twoSum(new int[]{2, 7, 11, 15}, 9, true));
        TestUtil.run("Test Case #2", new int[]{1, 2}, solution.twoSum(new int[]{3, 2, 4}, 6, true));
        TestUtil.run("Test Case #3", new int[]{0, 1}, solution.twoSum(new int[]{3, 3}, 6, true));
    }
}
