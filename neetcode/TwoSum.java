package neetcode;

import java.util.HashMap;
import java.util.Map;

import utils.TestUtil;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> existing = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (existing.containsKey(target - nums[i])) {
                return new int[]{existing.get(target - nums[i]), i};
            } else {
                existing.put(nums[i], i);
            }
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        TwoSum solution = new TwoSum();

        TestUtil.run("Test Case #1", new int[]{0, 1}, solution.twoSum(new int[]{2, 7, 11, 15}, 9));
        TestUtil.run("Test Case #2", new int[]{1, 2}, solution.twoSum(new int[]{3, 2, 4}, 6));
        TestUtil.run("Test Case #3", new int[]{0, 1}, solution.twoSum(new int[]{3, 3}, 6));
    }
}
