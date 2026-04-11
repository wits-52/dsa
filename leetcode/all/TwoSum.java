package leetcode.all;

import java.util.*;

public class TwoSum {
    private int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seenNumIdx = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (seenNumIdx.containsKey(target - nums[i])) {
                return new int[]{i, seenNumIdx.get(target - nums[i])};
            }

            seenNumIdx.put(nums[i], i);
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        TwoSum solution = new TwoSum();

        int[] res = solution.twoSum(new int[]{2,7,11,15}, 9);
        System.out.printf("%d %d\n", res[0], res[1]);

        res = solution.twoSum(new int[]{3,2,4}, 6);
        System.out.printf("%d %d\n", res[0], res[1]);

        res = solution.twoSum(new int[]{3,3}, 6);
        System.out.printf("%d %d\n", res[0], res[1]);

    }
}