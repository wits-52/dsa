package leetcode.all;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    private int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1);

        int currentSum = 0, result = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];

            result += prefixSumCount.getOrDefault(currentSum - k, 0);
            
            prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        }
        
        return result;
    }
    public static void main(String[] args) {
        SubarraySumEqualsK solution = new SubarraySumEqualsK();

        System.out.println(solution.subarraySum(new int[]{1,1,1}, 2));
        System.out.println(solution.subarraySum(new int[]{1,2,3}, 3));
        System.out.println(solution.subarraySum(new int[]{1,2,3,4,3,2,1}, 3));
    }
}
